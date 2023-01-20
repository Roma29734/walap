package com.example.walap.data.pager

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.walap.data.model.photoModelItem.PhotoModelItem
import com.example.walap.data.repository.WallpaperRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class PagerRepository @Inject constructor(
    private val repository: WallpaperRepository
) {

    fun getMovie(): Flowable<PagingData<PhotoModelItem>> {
        Log.d("aboa","запрос в ПагерРепазитории")
        val pager = Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true,
                maxSize = 50,
                prefetchDistance = 5,
                initialLoadSize = 20
            )
        ) {
            GetPhotoPager(repository)
        }.flowable

        return pager
    }
}