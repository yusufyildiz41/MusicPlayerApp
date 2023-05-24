package com.yusufyildiz.musicapp.data.model.artistlistmodel

import com.google.gson.annotations.SerializedName

data class ArtistModel(
    val id: Int?=null,
    val name: String?=null,
    val picture: String?=null,
    @SerializedName("picture_big")
    val pictureBig: String?=null,
    @SerializedName("picture_medium")
    val pictureMedium: String?=null,
    @SerializedName("picture_small")
    val pictureSmall: String?=null,
    @SerializedName("picture_xl")
    val pictureXl: String?=null,
    val radio: Boolean,
    val tracklist: String?=null,
    val type: String?=null
)