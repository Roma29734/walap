package com.example.walap.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.walap.data.model.photoModelItem.PhotoModelItem
import com.example.walap.data.pager.PagerRepository
import com.example.walap.data.repository.WallpaperRepository
import com.example.walap.utils.Resource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: WallpaperRepository,
    private val pagerRepository: PagerRepository,
): ViewModel() {

    private val _movieList = MutableLiveData<Resource<PagingData<PhotoModelItem>>>()
    var movieList: LiveData<Resource<PagingData<PhotoModelItem>>> = _movieList

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getWallpaper() {
        _movieList.postValue(Resource.Loading())
//        pagerRepository.getMovie()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ result ->
//                _movieList.postValue(Resource.Success(result))
//            }, { exeption ->
//                _movieList.postValue(Resource.Error(exeption.message.toString()))
//            })


        pagerRepository.getMovie()
            .cachedIn(viewModelScope)
            .subscribeOn(Schedulers.io())
            .subscribeBy(onNext = {
            _movieList.postValue(Resource.Success(it))
        }, onError = {
            _movieList.postValue(Resource.Error(it.message.toString()))
        }).addTo(compositeDisposable = CompositeDisposable())

    }


//    private val _photoTop: MutableLiveData<Resource<PhotoModel>> = MutableLiveData()
//    val photoTop get() = _photoTop
//
//
//
//
//    fun getWallpaper() {
//        _photoTop.postValue(Resource.Loading())
//        repository.getTopPhoto(1)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ result ->
//                Log.d("abobaperfection","Пришли данные в вью модель")
//                _photoTop.postValue(Resource.Success(result))
//            }, { exception ->
//                _photoTop.postValue(Resource.Error(exception.message.toString(), null))
//                Log.d("HomeViewModel","$exception")
//            })
//    }
}