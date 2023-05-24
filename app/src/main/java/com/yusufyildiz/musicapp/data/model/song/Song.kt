package com.yusufyildiz.musicapp.data.model.song

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "song")
data class Song(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "song_id")
    val songId: Long?=null,

    @ColumnInfo(name = "song_name")
    val songName: String?=null,

    @ColumnInfo(name = "song_duration")
    val songDuration: String?=null,

    @ColumnInfo(name = "song_image")
    val songImage: String?=null,

    @ColumnInfo(name = "is_favourite")
    var isFavourite: Boolean = false,

    @ColumnInfo(name = "song_preview")
    val songPreview: String?=null,

    @ColumnInfo(name = "date")
    val date: LocalDateTime = LocalDateTime.now()
)