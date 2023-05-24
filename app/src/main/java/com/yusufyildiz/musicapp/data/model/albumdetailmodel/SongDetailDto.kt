package com.yusufyildiz.musicapp.data.model.albumdetailmodel

import com.google.gson.annotations.SerializedName
import com.yusufyildiz.musicapp.data.model.song.Song

data class SongDetailDto(
    val album: Album?=null,
    val artist: ArtistX?=null,
    val duration: Int?=null,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int?=null,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int?=null,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean?=null,
    val id: Long?=null,
    val link: String?=null,
    @SerializedName("md5_image")
    val md5Image: String?=null,
    val preview: String?=null,
    val rank: Int?=null,
    val readable: Boolean?=null,
    val title: String?=null,
    @SerializedName("title_short")
    val titleShort: String?=null,
    @SerializedName("title_version")
    val titleVersion: String?=null,
    val type: String?=null
) {
    fun toSong(): Song {
        val minute = "${duration.toString().toInt() / 60} dk ${duration.toString().toInt() % 60} sn"
        return Song(
            songId = id,
            songName = title,
            songImage = album?.coverMedium,
            songPreview = preview,
            songDuration = minute
        )
    }
}