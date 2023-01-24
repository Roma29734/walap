package com.example.walap.data.repository

import com.example.walap.data.model.PhotoModel
import com.example.walap.data.model.SearchModel
import com.example.walap.data.model.entity.CategoriesEntity
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface WallpaperRepository {

    fun getTopPhoto(page: Int): Single<PhotoModel>

    fun getRandomPhoto(count: Int): Observable<PhotoModel>

    fun getSearchPhoto(query: String, page: Int): Single<SearchModel>

//    Local
    fun insertCategories(categoriesEntity: CategoriesEntity)

    fun readCategories(): Flowable<List<CategoriesEntity>>

    fun getSizeCategoriesTable(): Single<Int>
}