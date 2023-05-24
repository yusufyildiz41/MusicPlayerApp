package com.yusufyildiz.musicapp.data.model.albumdetailmodel

import com.google.gson.annotations.SerializedName

data class AlbumDetailsDataModel(
    val artist: Artist?=null,
    val available: Boolean?=null,
    val contributors: List<Contributor>?=null,
    val cover: String?=null,
    @SerializedName("cover_big")
    val coverBig: String?=null,
    @SerializedName("cover_medium")
    val coverMedium: String?=null,
    @SerializedName("cover_small")
    val coverSmall: String?=null,
    @SerializedName("cover_xl")
    val coverXL: String?=null,
    val duration: Int?=null,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int?=null,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int?=null,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean?=null,
    val fans: Int?=null,
    @SerializedName("genre_id")
    val genreId: Int?=null,
    val genres: Genres?=null,
    val id: Long?=null,
    val label: String?=null,
    val link: String?=null,
    @SerializedName("md5_image")
    val md5Image: String?=null,
    @SerializedName("nb_tracks")
    val nbTracks: Int?=null,
    @SerializedName("record_type")
    val recordType: String?=null,
    @SerializedName("release_date")
    val releaseDate: String?=null,
    val share: String?=null,
    val title: String?=null,
    val tracklist: String?=null,
    @SerializedName("tracks")
    val songs: Songs?=null,
    val type: String?=null,
    val upc: String?=null
)