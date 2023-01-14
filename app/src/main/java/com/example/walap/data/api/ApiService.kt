package com.example.walap.data.api

import com.example.walap.data.model.PhotoModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//https://api.unsplash.com/photos?page=1&client_id=_

    @GET("photos?")
    fun getTopPhoto(
        @Query("page") page: Int,
        @Query("client_id") client_id: String,
    ): Observable<PhotoModel>
}