package com.yusufyildiz.musicapp.data.model.artistdetailmodel

import com.google.gson.annotations.SerializedName

data class ArtistDetailModel(
    val id: Int?=null,
    val link: String?=null,
    val name: String?=null,
    @SerializedName("nb_album")
    val albumTotal: Int?=null,
    @SerializedName("nb_fan")
    val fanTotal: Int?=null,
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
    val share: String?=null,
    val tracklist: String?=null,
    val type: String?=null
)