package com.yusufyildiz.musicapp.di

import androidx.media3.exoplayer.ExoPlayer
import com.yusufyildiz.musicapp.data.source.local.LocalDataSourceImpl
import com.yusufyildiz.musicapp.data.source.local.SongFavouriteDAO
import com.yusufyildiz.musicapp.data.source.remote.DeezerAPI
import com.yusufyildiz.musicapp.data.source.remote.RemoteDataSourceImpl
import com.yusufyildiz.musicapp.domain.source.local.LocalDataSource
import com.yusufyildiz.musicapp.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        deezerAPI: DeezerAPI,
        exoPlayer: ExoPlayer,
        @IODispatcherInterceptorCoroutine ioDispatcher: CoroutineContext,
        @MainDispatcherInterceptorCoroutine mainDispatcher: CoroutineContext
    ): RemoteDataSource = RemoteDataSourceImpl(deezerAPI,exoPlayer,ioDispatcher,mainDispatcher)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        songFavouriteDAO: SongFavouriteDAO,
        @IODispatcherInterceptorCoroutine ioDispatcher: CoroutineContext
    ): LocalDataSource = LocalDataSourceImpl(songFavouriteDAO,ioDispatcher)
}