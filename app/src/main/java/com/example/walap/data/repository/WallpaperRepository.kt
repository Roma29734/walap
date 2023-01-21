package com.example.walap.data.repository

import com.example.walap.data.model.PhotoModel
import com.example.walap.data.model.SearchModel
import com.example.walap.data.model.entity.CategoriesEntity
import com.example.walap.utils.ResultRepo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface WallpaperRepository {

    fun getTopPhoto(page: Int): Single<PhotoModel>?

    fun getRandomPhoto(count: Int): Observable<PhotoModel>?

    fun getTopPhotoPro(page: Int): Single<ResultRepo<PhotoModel>>

    fun getSearchPhoto(query: String, page: Int): Single<ResultRepo<SearchModel>>

//    fun getTestPhoto(page: Int): Single<ResultRepo<PhotoModel>>
    fun insertCategories(categoriesEntity: CategoriesEntity)

    fun readCategories(): Flowable<List<CategoriesEntity>>

    fun getSizeCategoriesTable(): Single<Int>
}