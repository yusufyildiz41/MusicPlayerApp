package com.yusufyildiz.musicapp.data.source.remote

import com.yusufyildiz.musicapp.data.model.albumdetailmodel.AlbumDetailsDataModel
import com.yusufyildiz.musicapp.data.model.albumlistmodel.AlbumListDataModel
import com.yusufyildiz.musicapp.data.model.artistdetailmodel.ArtistDetailModel
import com.yusufyildiz.musicapp.data.model.artistlistmodel.ArtistListDataModel
import com.yusufyildiz.musicapp.data.model.musiccategorymodel.MusicCategoryListModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerAPI {

    @GET("/genre")
    suspend fun getMusicCategoryList(): MusicCategoryListModel

    @GET("/genre/{genre_id}/artists")
    suspend fun getArtistsByCategoryName(@Path("genre_id") categoryId: Int): ArtistListDataModel

    @GET("/artist/{artist_id}")
    suspend fun getArtistDetailByArtistId(@Path("artist_id") artistId: Int): ArtistDetailModel

    @GET("/artist/{artist_id}/albums")
    suspend fun getAlbumListByArtistId(@Path("artist_id") artistId: Int): AlbumListDataModel

    @GET("/album/{album_id}")
    suspend fun getSongListByAlbumId(@Path("album_id") albumId: Long): AlbumDetailsDataModel
}