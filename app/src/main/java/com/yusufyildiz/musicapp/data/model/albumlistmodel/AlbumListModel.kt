package com.yusufyildiz.musicapp.data.model.albumlistmodel

import com.google.gson.annotations.SerializedName

data class AlbumListModel(
    val cover: String?=null,
    @SerializedName("cover_big")
    val coverBig: String?=null,
    @SerializedName("cover_medium")
    val coverMedium: String?=null,
    @SerializedName("cover_small")
    val coverSmall: String?=null,
    @SerializedName("cover_xl")
    val coverXL: String?=null,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean?=null,
    val fans: Int?=null,
    @SerializedName("genre_id")
    val genreId: Int?=null,
    val id: Long?=null,
    val link: String?=null,
    @SerializedName("md5_image")
    val md5Image: String?=null,
    @SerializedName("record_type")
    val recordType: String?=null,
    @SerializedName("release_date")
    val releaseDate: String?=null,
    val title: String?=null,
    val tracklist: String?=null,
    val type: String?=null
)