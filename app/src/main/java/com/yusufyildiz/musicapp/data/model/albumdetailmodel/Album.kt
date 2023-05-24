package com.yusufyildiz.musicapp.data.model.albumdetailmodel

import com.google.gson.annotations.SerializedName

data class Album(
    val cover: String?=null,
    @SerializedName("cover_big")
    val coverBig: String?=null,
    @SerializedName("cover_medium")
    val coverMedium: String?=null,
    @SerializedName("cover_small")
    val coverSmall: String?=null,
    @SerializedName("cover_xl")
    val coverXL: String?=null,
    val id: Int?=null,
    @SerializedName("md5_image")
    val md5Image: String?=null,
    val title: String?=null,
    val tracklist: String?=null,
    val type: String?=null
)