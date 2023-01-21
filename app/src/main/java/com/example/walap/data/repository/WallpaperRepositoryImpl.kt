package com.example.walap.data.repository

import android.util.Log
import com.example.walap.data.local.dao.WallpaperDao
import com.example.walap.data.remote.api.RetrofitInstance
import com.example.walap.data.model.PhotoModel
import com.example.walap.data.model.SearchModel
import com.example.walap.data.model.entity.CategoriesEntity
import com.example.walap.utils.API_KEY
import com.example.walap.utils.ResultRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    instance: RetrofitInstance,
    private val dao: WallpaperDao,
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

    override fun getSearchPhoto(query: String, page: Int): Single<ResultRepo<SearchModel>> {
        val possibleRefund =
            apiService.getSearchPhoto(query = query, page = page, client_id = API_KEY)
        Log.d("checkBagInIthernetConnect", " засунул данные в переменную RepositoryWallpaper")
        return try {
            Log.d("checkBagInIthernetConnect", " зашел в try")
            possibleRefund
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
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
            val result = ResultRepo.Error(e, SearchModel(0, 0 , results = ArrayList()))
            Single.just(result)
        }
    }


// Local

    override fun insertCategories(categoriesEntity: CategoriesEntity) {
//        Observable.just(categoriesEntity)
//            .observeOn(Schedulers.io())
//            .subscribeBy (onNext = {
//                dao.insertCategories(it)
//            }, onError = {
//
//            })
        dao.insertCategories(categoriesEntity)
    }


    override fun readCategories(): Flowable<List<CategoriesEntity>> {
        return dao.readCategories()
    }

    override fun getSizeCategoriesTable(): Single<Int> {
        return dao.getSizeCategoriesTable()
    }
}
