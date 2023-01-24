package com.example.walap.ui.screen.topPhoto

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

class TopPhotoViewModel @Inject constructor(
    private val pagerRepository: PagerRepository,
) : ViewModel() {

    private val _movieList = MutableLiveData<Resource<PagingData<PhotoModelItem>>>()
    var movieList: LiveData<Resource<PagingData<PhotoModelItem>>> = _movieList

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getWallpaper() {
        _movieList.postValue(Resource.Loading())
        pagerRepository.getMovie()
            .cachedIn(viewModelScope)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _movieList.postValue(Resource.Success(result))
            }, { exeption ->
                _movieList.postValue(Resource.Error(exeption.message.toString()))
            })
    }
}