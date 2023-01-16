package com.example.walap.data.api

import com.example.walap.data.model.PhotoModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//https://api.unsplash.com/photos?page=1&client_id=_
//https://api.unsplash.com/photos/random?count=2&client_id=

    @GET("photos?")
    fun getTopPhoto(
        @Query("page") page: Int,
        @Query("client_id") client_id: String,
    ): Single<PhotoModel>

    @GET("photos/random?")
    fun getRandomPhoto(
        @Query("count") count: Int,
        @Query("client_id") client_id: String,
    ): Observable<PhotoModel>
}