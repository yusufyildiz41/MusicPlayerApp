package com.yusufyildiz.musicapp.presentation.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.databinding.FavouriteMusicListRecyclerRowBinding
import com.yusufyildiz.musicapp.data.model.song.Song
import javax.inject.Inject

class FavouriteMusicListRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<FavouriteMusicListRecyclerAdapter.FavouriteMusicListViewHolder>() {

    var onFavouriteClick: (Song) -> Unit = {}
    var onSongClick: (Song) -> Unit = {}

    var favouriteMusicList: List<Song>
        get() = recyclerListDiffer.currentList
        set(value){
            recyclerListDiffer.submitList(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteMusicListViewHolder {
        val binding = FavouriteMusicListRecyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavouriteMusicListViewHolder(binding, onFavouriteClick, onSongClick, glide)
    }

    override fun onBindViewHolder(holder: FavouriteMusicListViewHolder, position: Int) {
        holder.bind(favouriteMusicList[position])
    }

    class FavouriteMusicListViewHolder(
        val binding: FavouriteMusicListRecyclerRowBinding,
        val onFavouriteIconClick: (Song) -> Unit,
        val onSongClick: (Song) -> Unit,
        val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(song: Song) {
            with(binding) {
                cardView.setOnClickListener {
                    onSongClick(song)
                }
                addFavouriteButton.setOnClickListener {
                    onFavouriteIconClick(song)
                }
                songNameTextView.text = song.songName
                songDurationTextView.text = song.songDuration
                glide.load(song.songImage).into(albumImageView)
            }
        }

    }

    override fun getItemCount(): Int = favouriteMusicList.size

    private val diffUtil = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

}