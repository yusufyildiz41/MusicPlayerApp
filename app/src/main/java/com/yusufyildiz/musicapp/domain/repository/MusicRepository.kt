package com.yusufyildiz.musicapp.domain.repository

import com.yusufyildiz.musicapp.data.model.albumdetailmodel.AlbumDetailsDataModel
import com.yusufyildiz.musicapp.data.model.albumlistmodel.AlbumListDataModel
import com.yusufyildiz.musicapp.data.model.artistdetailmodel.ArtistDetailModel
import com.yusufyildiz.musicapp.data.model.artistlistmodel.ArtistListDataModel
import com.yusufyildiz.musicapp.data.model.musiccategorymodel.MusicCategoryListModel
import com.yusufyildiz.musicapp.data.model.song.Song

interface MusicRepository {
    suspend fun getMusicCategoryList(): MusicCategoryListModel
    suspend fun getArtistListByCategory(categoryId: Int): ArtistListDataModel
    suspend fun getArtistDetailByArtistId(artistId: Int): ArtistDetailModel
    suspend fun getAlbumListByArtistId(artistId: Int): AlbumListDataModel
    suspend fun getSongListByAlbumId(albumId: Long): List<Song>
    suspend fun addSongToFavourites(song: Song)
    suspend fun deleteSongFromFavourites(song: Song)
    suspend fun getFavouriteSongList(): List<Song>?
    suspend fun playSongWithSongURL(songURL: String)
    suspend fun stopSong()
}