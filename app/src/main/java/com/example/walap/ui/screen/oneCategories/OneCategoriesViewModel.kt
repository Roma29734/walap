package com.example.walap.ui.screen.oneCategories

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

class OneCategoriesViewModel @Inject constructor(
    private val pagerRepository: PagerRepository
) : ViewModel() {

    private val _oneCategoriesList = MutableLiveData<Resource<PagingData<PhotoModelItem>>>()
    var oneCategoriesList: LiveData<Resource<PagingData<PhotoModelItem>>> = _oneCategoriesList

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getWallpaper(query: String) {
        _oneCategoriesList.postValue(Resource.Loading())
        pagerRepository.getSearchPhoto(query = query)
            .cachedIn(viewModelScope)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = { result ->
                _oneCategoriesList.value = Resource.Success(result)
            }, onError = { exeption ->
                _oneCategoriesList.postValue(Resource.Error(exeption.message.toString()))
            })

    }
}