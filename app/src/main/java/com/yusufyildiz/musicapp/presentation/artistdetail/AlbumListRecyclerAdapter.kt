package com.yusufyildiz.musicapp.presentation.artistdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.R
import com.yusufyildiz.musicapp.common.extension.toDate
import com.yusufyildiz.musicapp.data.model.albumlistmodel.AlbumListModel
import com.yusufyildiz.musicapp.databinding.AlbumListRecyclerRowBinding
import javax.inject.Inject

class AlbumListRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<AlbumListRecyclerAdapter.AlbumListViewHolder>(){

    var onAlbumClick: (AlbumListModel) -> Unit = {}

    var albumList: List<AlbumListModel>
        get() = recyclerListDiffer.currentList
        set(value)= recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        val binding = AlbumListRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AlbumListViewHolder(binding, glide, onAlbumClick)
    }

    override fun onBindViewHolder(holder: AlbumListViewHolder, position: Int) {
        holder.bind(albumList[position])
    }

    class AlbumListViewHolder(
        val binding: AlbumListRecyclerRowBinding,
        val glide: RequestManager,
        val onAlbumClick: (AlbumListModel) -> Unit
        ): RecyclerView.ViewHolder(binding.root){

            fun bind(album: AlbumListModel){
                with(binding){
                    cardView.setOnClickListener {
                        onAlbumClick(album)
                    }
                    albumNameTextView.text = album.title
                    releaseDateTextView.text = "Çıkış tarihi : ${album.releaseDate?.toDate()}"
                    glide.load(album.coverMedium).into(albumImageView)
                }
            }
    }

    override fun getItemCount(): Int = albumList.size

    private val diffUtil =object: DiffUtil.ItemCallback<AlbumListModel>() {
        override fun areItemsTheSame(oldItem: AlbumListModel, newItem: AlbumListModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: AlbumListModel, newItem: AlbumListModel): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

}