package com.example.walap.data.repository

import com.example.walap.data.api.RetrofitInstance
import com.example.walap.data.model.PhotoModel
import com.example.walap.utils.API_KEY
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    instance: RetrofitInstance
) : WallpaperRepository {

    private val apiService = instance.api

    override fun getTopPhoto(page: Int): Observable<PhotoModel> {
        return apiService.getTopPhoto(page = page, client_id = API_KEY)
    }

    override fun getRandomPhoto(count: Int): Observable<PhotoModel> {
        return apiService.getRandomPhoto(count, client_id = API_KEY)
    }
}