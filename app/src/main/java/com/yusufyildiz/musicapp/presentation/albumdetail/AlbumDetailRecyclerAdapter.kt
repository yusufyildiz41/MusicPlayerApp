package com.yusufyildiz.musicapp.presentation.albumdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.R
import com.yusufyildiz.musicapp.databinding.SongListRecyclerRowBinding
import com.yusufyildiz.musicapp.data.model.song.Song
import javax.inject.Inject

class AlbumDetailRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<AlbumDetailRecyclerAdapter.AlbumDetailViewHolder>() {

    var onFavouriteClick: (Song) -> Unit = {}
    var onSongClick: (Song) -> Unit = {}

    var songList: List<Song>
        get() = recyclerListDiffer.currentList
        set(value) {
            recyclerListDiffer.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val binding =
            SongListRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumDetailViewHolder(
            binding,
            onSongClick,
            onFavouriteClick,
            glide
        )
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        holder.bind(songList[position])
    }

    class AlbumDetailViewHolder(
        val binding: SongListRecyclerRowBinding,
        val onSongClick: (Song) -> Unit,
        val onFavouriteClick: (Song) -> Unit,
        val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            with(binding) {
                cardView.setOnClickListener {
                    onSongClick(song)
                }
                addFavouriteButton.setBackgroundResource(
                    if (song.isFavourite) R.drawable.favourite_icon_filled
                    else R.drawable.favourite_icon_empty
                )

                addFavouriteButton.setOnClickListener {
                    onFavouriteClick(song)
                }

                songNameTextView.text = song.songName
                songDurationTextView.text = song.songDuration
                glide.load(song.songImage).into(albumImageView)
            }
        }
    }

    override fun getItemCount(): Int = songList.size

    private val diffUtil = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.songId == newItem.songId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

}