package com.example.walap.ui.screen.oneCategories

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
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class OneCategoriesViewModel @Inject constructor(
    private val pagerRepository: PagerRepository
) : ViewModel() {

    private val _oneCategoriList = MutableLiveData<Resource<PagingData<PhotoModelItem>>>()
    var oneCategoriList: LiveData<Resource<PagingData<PhotoModelItem>>> = _oneCategoriList

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getWallpaper(query: String) {
        _oneCategoriList.postValue(Resource.Loading())
        Log.d("amega3","отправил запрос на получение")
        pagerRepository.getSearchPhoto(query = query)
            .cachedIn(viewModelScope)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d("amega3","подписался на ответ ")
                _oneCategoriList.value = Resource.Success(result)
                Log.d("amega3","присвоил ответ")
            }, { exeption ->
                _oneCategoriList.postValue(Resource.Error(exeption.message.toString()))
            })
    }
}