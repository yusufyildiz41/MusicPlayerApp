package com.yusufyildiz.musicapp.data.model.albumlistmodel

import com.google.gson.annotations.SerializedName

data class AlbumListDataModel(
    @SerializedName("data")
    val albumList: List<AlbumListModel>?=null,
    val next: String?=null,
    val total: Int?=null
)