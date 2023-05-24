package com.yusufyildiz.musicapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @IODispatcherInterceptorCoroutine
    @Provides
    @Singleton
    fun provideCoroutineIODispatcher(): CoroutineContext = Dispatchers.IO

    @MainDispatcherInterceptorCoroutine
    @Provides
    @Singleton
    fun provideCoroutineMainDispatcher(): CoroutineContext = Dispatchers.Main



}