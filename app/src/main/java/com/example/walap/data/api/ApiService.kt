package com.example.walap.data.api

import com.example.walap.data.model.PhotoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//https://api.unsplash.com/photos?page=1&client_id=_jsturDm6uZ8Rp4RVK73glpy84X2eDku7oWPKfHR6KM

    @GET("photos?")
    suspend fun getTopPhoto(
        @Query("page") page: Int,
        @Query("client_id") client_id: String,
    ): PhotoModel
}