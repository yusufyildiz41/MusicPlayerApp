package com.yusufyildiz.musicapp.data.model.musiccategorymodel

import com.google.gson.annotations.SerializedName

data class MusicCategoryListModel(
    @SerializedName("data")
    val musicCategoryList: List<MusicCategoryDetailModel>?=null
)