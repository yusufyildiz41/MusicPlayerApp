package com.yusufyildiz.musicapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcherInterceptorCoroutine

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcherInterceptorCoroutine