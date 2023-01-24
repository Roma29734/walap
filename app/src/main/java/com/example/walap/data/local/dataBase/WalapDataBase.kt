package com.example.walap.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.walap.data.local.dao.WallpaperDao
import com.example.walap.data.model.entity.CategoriesEntity

@Database(
    entities = [CategoriesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WalapDataBase : RoomDatabase() {
    abstract fun wallpaperDao(): WallpaperDao
}
