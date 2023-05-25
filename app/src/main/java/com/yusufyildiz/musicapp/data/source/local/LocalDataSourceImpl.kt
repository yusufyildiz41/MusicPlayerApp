package com.yusufyildiz.musicapp.data.source.local

import com.yusufyildiz.musicapp.data.model.song.Song
import com.yusufyildiz.musicapp.di.IODispatcherInterceptorCoroutine
import com.yusufyildiz.musicapp.domain.source.local.LocalDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocalDataSourceImpl @Inject constructor(
    private val songFavouriteDAO: SongFavouriteDAO,
    @IODispatcherInterceptorCoroutine private val ioDispatcher: CoroutineContext
): LocalDataSource {
    override suspend fun addSongToFavourites(song: Song)= withContext(ioDispatcher){
        songFavouriteDAO.addSongToFavourites(song)
    }

    override suspend fun deleteSongFromFavourites(song: Song) = withContext(ioDispatcher){
        songFavouriteDAO.deleteSongFromFavourites(song)
    }

    override suspend fun getFavouriteSongList() = withContext(ioDispatcher){
        songFavouriteDAO.getFavouriteSongList()
    }

    override suspend fun getSongIdList() = withContext(ioDispatcher) {
        songFavouriteDAO.getSongIdList()
    }
}