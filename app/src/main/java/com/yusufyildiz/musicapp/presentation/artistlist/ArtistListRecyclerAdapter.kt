package com.yusufyildiz.musicapp.presentation.artistlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.data.model.artistlistmodel.ArtistModel
import com.yusufyildiz.musicapp.databinding.ArtistListRecyclerRowBinding
import javax.inject.Inject

class ArtistListRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
): RecyclerView.Adapter<ArtistListRecyclerAdapter.ArtistListViewHolder>() {

    var onArtistClick: (ArtistModel) -> Unit = {}

    var artistList: List<ArtistModel>
        get() = recyclerListDiffer.currentList
        set(value)= recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistListViewHolder {
        val binding = ArtistListRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtistListViewHolder(binding,onArtistClick, glide)
    }

    override fun onBindViewHolder(holder: ArtistListViewHolder, position: Int) {
        holder.bind(artistList[position])
    }

    class ArtistListViewHolder(
        val binding: ArtistListRecyclerRowBinding,
        val onArtistClick: (ArtistModel) -> Unit,
        val glide: RequestManager
    ): RecyclerView.ViewHolder(binding.root) {
            fun bind(artist: ArtistModel){
                with(binding){
                    artistListCardView.setOnClickListener {
                        onArtistClick(artist)
                    }
                    artistNameTextView.text = artist.name
                    glide.load(artist.pictureBig).into(artistImageView)
                }
            }
        }

    override fun getItemCount(): Int = artistList.size

    private val diffUtil =object: DiffUtil.ItemCallback<ArtistModel>() {
        override fun areItemsTheSame(oldItem: ArtistModel, newItem: ArtistModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ArtistModel, newItem: ArtistModel): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

}
