package com.yusufyildiz.musicapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusufyildiz.musicapp.data.model.song.Song

@Dao
interface SongFavouriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSongToFavourites(song: Song)

    @Delete
    suspend fun deleteSongFromFavourites(song: Song)

    @Query("SELECT * FROM song ORDER BY date DESC")
    suspend fun getFavouriteSongList(): List<Song>?

    @Query("SELECT song_id FROM song")
    suspend fun getSongIdList(): List<Long>?
}