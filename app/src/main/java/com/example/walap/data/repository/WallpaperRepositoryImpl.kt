package com.example.walap.data.repository

import com.example.walap.data.local.dao.WallpaperDao
import com.example.walap.data.remote.api.RetrofitInstance
import com.example.walap.data.model.PhotoModel
import com.example.walap.data.model.SearchModel
import com.example.walap.data.model.entity.CategoriesEntity
import com.example.walap.utils.API_KEY
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    instance: RetrofitInstance,
    private val dao: WallpaperDao,
) : WallpaperRepository {

    private val apiService = instance.api

    override fun getTopPhoto(page: Int): Single<PhotoModel> {
        return apiService.getTopPhoto(page = page, client_id = API_KEY)
    }

    override fun getRandomPhoto(count: Int): Observable<PhotoModel> {
        return apiService.getRandomPhoto(count, client_id = API_KEY)
    }

    override fun getSearchPhoto(query: String, page: Int): Single<SearchModel> {
        return apiService.getSearchPhoto(query = query, page = page, client_id = API_KEY)
    }


// Local

    override fun insertCategories(categoriesEntity: CategoriesEntity) {
        dao.insertCategories(categoriesEntity)
    }


    override fun readCategories(): Flowable<List<CategoriesEntity>> {
        return dao.readCategories()
    }

    override fun getSizeCategoriesTable(): Single<Int> {
        return dao.getSizeCategoriesTable()
    }
}
