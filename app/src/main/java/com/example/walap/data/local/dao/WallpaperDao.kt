package com.example.walap.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.walap.data.model.entity.CategoriesEntity
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface WallpaperDao {

    @Insert(entity = CategoriesEntity::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertCategories(categoriesEntity: CategoriesEntity)

    @Query("SELECT * FROM categories_table")
    fun readCategories(): Flowable<List<CategoriesEntity>>

    @Query("SELECT COUNT(*) FROM categories_table")
    fun getSizeCategoriesTable(): Single<Int>
}
