package com.example.walap.di.module

import android.app.Application
import androidx.room.Room
import com.example.walap.data.local.dao.WallpaperDao
import com.example.walap.data.local.dataBase.WalapDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    fun provideWallpaperDao(appDataBase: WalapDataBase): WallpaperDao = appDataBase.wallpaperDao()

    @Provides
    @Singleton
    fun provideWalapDataBase(context: Application): WalapDataBase =
        Room.databaseBuilder(
            context,
            WalapDataBase::class.java,
            "walap_base"
        ).build()
}

