package com.yusufyildiz.musicapp.data.model.musiccategorymodel

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MusicCategoryDetailModel(
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
    val type: String?=null
)