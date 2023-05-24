package com.yusufyildiz.musicapp.data.source.remote

import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.yusufyildiz.musicapp.domain.source.remote.RemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RemoteDataSourceImpl @Inject constructor(
    private val deezerAPI: DeezerAPI,
    private val exoPlayer: ExoPlayer,
    private val ioDispatcher: CoroutineContext,
    private val mainDispatcher: CoroutineContext
): RemoteDataSource {
    override suspend fun getMusicCategoryList() = withContext(ioDispatcher) {
        deezerAPI.getMusicCategoryList()
    }
    override suspend fun getArtistListByCategory(categoryId: Int) = withContext(ioDispatcher) {
        deezerAPI.getArtistsByCategoryName(categoryId)
    }
    override suspend fun getArtistDetailByArtistId(artistId: Int) = withContext(ioDispatcher){
        deezerAPI.getArtistDetailByArtistId(artistId)
    }
    override suspend fun getAlbumListByArtistId(artistId: Int) = withContext(ioDispatcher){
        deezerAPI.getAlbumListByArtistId(artistId)
    }
    override suspend fun getSongListByAlbumId(albumId: Long) = withContext(ioDispatcher){
        deezerAPI.getSongListByAlbumId(albumId)
    }
    override suspend fun playSongWithSongURL(songURL: String) = withContext(mainDispatcher){
        val mediaItem = MediaItem.fromUri(songURL)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    override suspend fun stopSong() {
        exoPlayer.stop()
    }
}