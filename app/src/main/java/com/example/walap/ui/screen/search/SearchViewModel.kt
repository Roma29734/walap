package com.example.walap.ui.screen.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.walap.data.model.photoModelItem.PhotoModelItem
import com.example.walap.data.remote.pager.PagerRepository
import com.example.walap.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val pagerRepository: PagerRepository
): ViewModel() {


    private val _searchWallpaperResult = MutableLiveData<Resource<PagingData<PhotoModelItem>>>()
    var searchWallpaperResult: LiveData<Resource<PagingData<PhotoModelItem>>> = _searchWallpaperResult

    @OptIn(ExperimentalCoroutinesApi::class)
    fun searchWallpaper(query: String) {
        _searchWallpaperResult.postValue(Resource.Loading())
        Log.d("acurat8","установил статус загрузка")
        pagerRepository.getSearchPhoto(query = query)
            .cachedIn(viewModelScope)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = { result ->
                Log.d("amega3","подписался на ответ ")
                _searchWallpaperResult.value = Resource.Success(result)
                Log.d("acurat8","установил статус все збс")
                Log.d("amega3","присвоил ответ")
            }, onError = { exeption ->
                _searchWallpaperResult.postValue(Resource.Error(exeption.message.toString()))
            })

    }
}