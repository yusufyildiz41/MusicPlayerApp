package com.yusufyildiz.musicapp.domain.source.local

import com.yusufyildiz.musicapp.data.model.song.Song

interface LocalDataSource {
    suspend fun addSongToFavourites(song: Song)
    suspend fun deleteSongFromFavourites(song: Song)
    suspend fun getFavouriteSongList(): List<Song>?
    suspend fun getSongIdList(): List<Long>?
}