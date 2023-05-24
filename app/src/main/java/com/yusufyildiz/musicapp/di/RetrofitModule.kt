package com.yusufyildiz.musicapp.di

import com.yusufyildiz.musicapp.common.util.Constants.BASE_URL
import com.yusufyildiz.musicapp.data.source.remote.DeezerAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): DeezerAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DeezerAPI::class.java)
}