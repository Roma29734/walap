package com.example.walap.di.module

import com.example.walap.data.local.dao.WallpaperDao
import com.example.walap.data.remote.api.RetrofitInstance
import com.example.walap.data.remote.pager.GetPhotoPager
import com.example.walap.data.repository.WallpaperRepository
import com.example.walap.data.repository.WallpaperRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {

    @Provides
    fun provideWallpaperRepository(impl: WallpaperRepositoryImpl): WallpaperRepository = impl

    @Provides
    fun provideWallpaperRepositoryImpl(
        instance: RetrofitInstance,
        dao: WallpaperDao
    ): WallpaperRepositoryImpl =
        WallpaperRepositoryImpl(instance, dao)

    @Provides
    fun provideRetrofitInstance(): RetrofitInstance = RetrofitInstance

    @Provides
    fun provideGetPhotoPager(repository: WallpaperRepository): GetPhotoPager =
        GetPhotoPager(repository)
}