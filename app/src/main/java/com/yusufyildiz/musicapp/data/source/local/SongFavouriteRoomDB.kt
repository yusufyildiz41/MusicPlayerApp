package com.yusufyildiz.musicapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yusufyildiz.musicapp.common.util.Converters
import com.yusufyildiz.musicapp.data.model.song.Song

@Database(entities = [Song::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SongFavouriteRoomDB: RoomDatabase() {
    abstract fun songFavouriteDAO(): SongFavouriteDAO
}