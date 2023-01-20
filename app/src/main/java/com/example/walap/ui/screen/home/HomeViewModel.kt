package com.example.walap.ui.screen.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.walap.data.model.photoModelItem.PhotoModelItem
import com.example.walap.data.pager.PagerRepository
import com.example.walap.data.repository.WallpaperRepository
import com.example.walap.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    application: Application,
    private val pagerRepository: PagerRepository,
): AndroidViewModel(application) {

    private val context = application

    private val _movieList = MutableLiveData<Resource<PagingData<PhotoModelItem>>>()
    var movieList: LiveData<Resource<PagingData<PhotoModelItem>>> = _movieList

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getWallpaper() {
        _movieList.postValue(Resource.Loading())
        Log.d("checkBagInIthernetConnect","Вызываю полученние данных ViewModel")
        pagerRepository.getMovie()
            .cachedIn(viewModelScope)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d("checkBagInIthernetConnect","Подписался на данные, обрабатываю их ViewModel")
                Log.d("aboa","$result")
                _movieList.postValue(Resource.Success(result))
                Log.d("aboa","нету ошибка")
            }, { exeption ->
                Log.d("checkBagInIthernetConnect","Зашел в ошибку ViewModel")
                Log.d("aboa","зашел в ошибку")
                _movieList.postValue(Resource.Error(exeption.message.toString()))
            })


//        pagerRepository.getMovie()
//            .cachedIn(viewModelScope)
//            .subscribeOn(Schedulers.io())
//            .subscribeBy(onNext = {
//            _movieList.postValue(Resource.Success(it))
//                Log.d("aboa","нету ошибка")
//        }, onError = {
//                Log.d("aboa","зашел в ошибку")
//            _movieList.postValue(Resource.Error(it.message.toString()))
//        }).addTo(compositeDisposable = CompositeDisposable())

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