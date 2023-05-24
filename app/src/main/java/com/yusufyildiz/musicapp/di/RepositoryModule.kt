package com.yusufyildiz.musicapp.di

import com.yusufyildiz.musicapp.data.repository.MusicRepositoryImpl
import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import com.yusufyildiz.musicapp.domain.source.local.LocalDataSource
import com.yusufyildiz.musicapp.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMusicRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): MusicRepository = MusicRepositoryImpl(remoteDataSource,localDataSource)
}