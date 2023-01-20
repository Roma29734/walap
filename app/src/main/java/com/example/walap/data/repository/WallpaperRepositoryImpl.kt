package com.example.walap.data.repository

import android.util.Log
import com.example.walap.data.api.RetrofitInstance
import com.example.walap.data.model.PhotoModel
import com.example.walap.utils.API_KEY
import com.example.walap.utils.ResultRepo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    instance: RetrofitInstance
) : WallpaperRepository {

    private val apiService = instance.api

    override fun getTopPhoto(page: Int): Single<PhotoModel>? {
       return apiService.getTopPhoto(page = page, client_id = API_KEY)
    }

    override fun getRandomPhoto(count: Int): Observable<PhotoModel>? {
        return try {
            apiService.getRandomPhoto(count, client_id = API_KEY)
        } catch (e: Exception) {
            null
        }
    }

    override fun getTopPhotoPro(page: Int): Single<ResultRepo<PhotoModel>> {
        Log.d("checkBagInIthernetConnect", " зашел в RepositoryWallpaper")

        val possibleRefund = apiService.getTopPhoto(page = page, client_id = API_KEY)
        Log.d("checkBagInIthernetConnect", " засунул данные в переменную RepositoryWallpaper")
        return try {
            Log.d("checkBagInIthernetConnect", " зашел в try")
            possibleRefund.flatMap {
                try {
                    Log.d(
                        "checkBagInIthernetConnect",
                        "flatMapnyl запрос, обрабатываю RepositoryWallpaper"
                    )
                    Log.d("checkIthernetConnect", "wallpaperRepo отправляю запрос наполучение")
                    val result = ResultRepo.Success(it)
                    Log.d("checkIthernetConnect", "получил данные")
                    Single.just(result)
                } catch (e: IOException) {
                    Log.d(
                        "checkBagInIthernetConnect",
                        "Словил ошибку, обрабатываю RepositoryWallpaper"
                    )
                    Log.d("checkIthernetConnect", "wallpaperRepo словил ошибку")
                    val result = ResultRepo.Error(e, it)
                    Single.just(result)
                }
            }
        } catch (e: Exception) {
            Log.d(
                "checkBagInIthernetConnect",
                "Словил ошибку в последнем catch, обрабатываю RepositoryWallpaper"
            )
            val result = ResultRepo.Error(e, PhotoModel())
            Single.just(result)
        }
    }

//    override fun getTestPhoto(page: Int): Single<ResultRepo<PhotoModel>> {
//
//        return Single.just(apiService.getPhoto(page = page, client_id = API_KEY))
//            .subscribeOn(Schedulers.io())
//            .flatMap { result ->
//                val aboba = ResultRepo.Success(result.execute().body())
//                Single.just(aboba)
//            }
//    }
}