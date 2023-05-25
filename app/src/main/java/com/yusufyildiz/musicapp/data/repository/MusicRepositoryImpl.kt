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
    override suspend fun getSongListByAlbumId(albumId: Long): List<Song> {
        val idList = localDataSource.getSongIdList()

        return remoteDataSource.getSongListByAlbumId(albumId).songs?.trackListData?.map {
            Song(
                songId = it.id,
                songName = it.title,
                songImage = it.album?.coverMedium,
                songPreview = it.preview,
                songDuration = "${
                    it.duration.toString().toInt() / 60
                } dk ${it.duration.toString().toInt() % 60} sn",
                isFavourite = idList?.contains(it.id) ?: false
            )
        }.orEmpty()
    }
    override suspend fun addSongToFavourites(song: Song) = localDataSource.addSongToFavourites(song)
    override suspend fun deleteSongFromFavourites(song: Song) = localDataSource.deleteSongFromFavourites(song)
    override suspend fun getFavouriteSongList() = localDataSource.getFavouriteSongList()
    override suspend fun playSongWithSongURL(songURL: String) = remoteDataSource.playSongWithSongURL(songURL)
    override suspend fun stopSong() = remoteDataSource.stopSong()
}