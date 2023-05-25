package com.yusufyildiz.musicapp.presentation.albumdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.R
import com.yusufyildiz.musicapp.databinding.SongListRecyclerRowBinding
import com.yusufyildiz.musicapp.data.model.song.Song
import javax.inject.Inject

class AlbumDetailRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
) : ListAdapter<Song, AlbumDetailRecyclerAdapter.AlbumDetailViewHolder>(AlbumDetailDiffUtilCallBack()) {

    var onFavouriteClick: (Song) -> Unit = {}
    var onSongClick: (Song) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val binding =
            SongListRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumDetailViewHolder(
            binding, onSongClick, onFavouriteClick, glide
        )
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        holder.bind(currentList[position])
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
                    addFavouriteButton.setBackgroundResource(
                        if (song.isFavourite) R.drawable.favourite_icon_empty
                        else R.drawable.favourite_icon_filled
                    )
                    onFavouriteClick(song)
                }

                songNameTextView.text = song.songName
                songDurationTextView.text = song.songDuration
                glide.load(song.songImage).into(albumImageView)
            }
        }
    }

    class AlbumDetailDiffUtilCallBack : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song) =
            oldItem.songId == newItem.songId

        override fun areContentsTheSame(oldItem: Song, newItem: Song) = oldItem == newItem
    }
}