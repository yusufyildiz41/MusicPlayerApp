package com.yusufyildiz.musicapp.presentation.musiccategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.data.model.musiccategorymodel.MusicCategoryDetailModel
import com.yusufyildiz.musicapp.databinding.MusicCategoryRecyclerRowBinding
import javax.inject.Inject

class MusicCategoryRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<MusicCategoryRecyclerAdapter.MusicCategoryViewHolder>() {

    var onCategoryClick: (MusicCategoryDetailModel) -> Unit = {}

    var musicCategoryList: List<MusicCategoryDetailModel>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicCategoryViewHolder {
        val binding = MusicCategoryRecyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MusicCategoryViewHolder(binding,onCategoryClick,glide)
    }

    override fun onBindViewHolder(holder: MusicCategoryViewHolder, position: Int) {
        holder.bind(musicCategoryList[position])
    }

    class MusicCategoryViewHolder(
        val binding: MusicCategoryRecyclerRowBinding,
        val onCategoryClick: (MusicCategoryDetailModel) -> Unit,
        val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: MusicCategoryDetailModel){
            with(binding){
                musicCategoryCardView.setOnClickListener {
                    onCategoryClick(category)
                }
                musicCategoryNameTextView.text = category.name
                glide.load(category.pictureBig).into(musicCategoryImageView)
            }
        }

    }

    override fun getItemCount(): Int = musicCategoryList.size

    private val diffUtil = object : DiffUtil.ItemCallback<MusicCategoryDetailModel>() {
        override fun areItemsTheSame(
            oldItem: MusicCategoryDetailModel,
            newItem: MusicCategoryDetailModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MusicCategoryDetailModel,
            newItem: MusicCategoryDetailModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)


}