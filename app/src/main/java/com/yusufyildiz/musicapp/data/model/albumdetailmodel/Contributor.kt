package com.yusufyildiz.musicapp.data.model.albumdetailmodel

import com.google.gson.annotations.SerializedName

data class Contributor(
    val id: Int?=null,
    val link: String?=null,
    val name: String?=null,
    val picture: String?=null,
    @SerializedName("picture_big")
    val pictureBig: String?=null,
    @SerializedName("picture_medium")
    val pictureMedium: String?=null,
    @SerializedName("picture_small")
    val pictureSmall: String?=null,
    @SerializedName("picture_xl")
    val pictureXL: String?=null,
    val radio: Boolean?=null,
    val role: String?=null,
    val share: String?=null,
    val tracklist: String?=null,
    val type: String?=null
)