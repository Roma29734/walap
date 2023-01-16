package com.example.walap.data.repository

import com.example.walap.data.model.PhotoModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface WallpaperRepository {
    fun getTopPhoto(page: Int): Single<PhotoModel>

    fun getRandomPhoto(count: Int): Observable<PhotoModel>
}