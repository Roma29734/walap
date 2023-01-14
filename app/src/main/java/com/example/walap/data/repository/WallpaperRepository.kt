package com.example.walap.data.repository

import com.example.walap.data.model.PhotoModel

interface WallpaperRepository {
    suspend fun getTopPhoto(page: Int): PhotoModel
}