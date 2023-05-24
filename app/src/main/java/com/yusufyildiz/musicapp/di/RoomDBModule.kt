package com.yusufyildiz.musicapp.di

import android.content.Context
import androidx.room.Room
import com.yusufyildiz.musicapp.data.source.local.SongFavouriteDAO
import com.yusufyildiz.musicapp.data.source.local.SongFavouriteRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideSongFavouriteRoomDB(
        @ApplicationContext context: Context
    ): SongFavouriteRoomDB = Room.databaseBuilder(
        context,
        SongFavouriteRoomDB::class.java,
        "song_database",
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideSongFavouriteDAO(
        songFavouriteRoomDB: SongFavouriteRoomDB
    ): SongFavouriteDAO = songFavouriteRoomDB.songFavouriteDAO()
}