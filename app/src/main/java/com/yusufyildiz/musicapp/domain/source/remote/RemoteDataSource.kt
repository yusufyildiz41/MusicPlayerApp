package com.yusufyildiz.musicapp.domain.source.remote

import com.yusufyildiz.musicapp.data.model.albumdetailmodel.AlbumDetailsDataModel
import com.yusufyildiz.musicapp.data.model.albumlistmodel.AlbumListDataModel
import com.yusufyildiz.musicapp.data.model.artistdetailmodel.ArtistDetailModel
import com.yusufyildiz.musicapp.data.model.artistlistmodel.ArtistListDataModel
import com.yusufyildiz.musicapp.data.model.musiccategorymodel.MusicCategoryListModel

interface RemoteDataSource {
    suspend fun getMusicCategoryList(): MusicCategoryListModel
    suspend fun getArtistListByCategory(categoryId: Int): ArtistListDataModel
    suspend fun getArtistDetailByArtistId(artistId: Int): ArtistDetailModel
    suspend fun getAlbumListByArtistId(artistId: Int): AlbumListDataModel
    suspend fun getSongListByAlbumId(albumId: Long): AlbumDetailsDataModel
    suspend fun playSongWithSongURL(songURL: String)
    suspend fun stopSong()
}