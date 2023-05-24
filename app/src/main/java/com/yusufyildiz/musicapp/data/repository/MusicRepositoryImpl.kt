package com.yusufyildiz.musicapp.data.repository

import com.yusufyildiz.musicapp.data.model.song.Song
import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import com.yusufyildiz.musicapp.domain.source.local.LocalDataSource
import com.yusufyildiz.musicapp.domain.source.remote.RemoteDataSource
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MusicRepository {
    override suspend fun getMusicCategoryList() = remoteDataSource.getMusicCategoryList()
    override suspend fun getArtistListByCategory(categoryId: Int) = remoteDataSource.getArtistListByCategory(categoryId)
    override suspend fun getArtistDetailByArtistId(artistId: Int) = remoteDataSource.getArtistDetailByArtistId(artistId)
    override suspend fun getAlbumListByArtistId(artistId: Int) = remoteDataSource.getAlbumListByArtistId(artistId)
    override suspend fun getSongListByAlbumId(albumId: Long) = remoteDataSource.getSongListByAlbumId(albumId)
    override suspend fun addSongToFavourites(song: Song) = localDataSource.addSongToFavourites(song)
    override suspend fun deleteSongFromFavourites(song: Song) = localDataSource.deleteSongFromFavourites(song)
    override suspend fun getFavouriteSongList() = localDataSource.getFavouriteSongList()
    override suspend fun searchSongWithSongId(songId: Long) = localDataSource.searchSongWithSongId(songId)
    override suspend fun playSongWithSongURL(songURL: String) = remoteDataSource.playSongWithSongURL(songURL)
    override suspend fun stopSong() = remoteDataSource.stopSong()
}