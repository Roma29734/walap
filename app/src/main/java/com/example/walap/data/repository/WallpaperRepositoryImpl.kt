package com.example.walap.data.repository

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.example.walap.data.api.RetrofitInstance
import com.example.walap.data.model.PhotoModel
import com.example.walap.utils.API_KEY
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    instance: RetrofitInstance
) : WallpaperRepository {

    private val apiService = instance.api

    override suspend fun getTopPhoto(page: Int): PhotoModel {
        return apiService.getTopPhoto(page = page, client_id = API_KEY)
    }

}