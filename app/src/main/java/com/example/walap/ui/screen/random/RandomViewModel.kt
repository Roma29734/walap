package com.example.walap.ui.screen.random

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walap.data.model.PhotoModel
import com.example.walap.data.repository.WallpaperRepository
import com.example.walap.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RandomViewModel @Inject constructor(
    private val repository: WallpaperRepository
): ViewModel() {

    private val _randomPhoto: MutableLiveData<Resource<PhotoModel>> = MutableLiveData()
    val randomPhoto get() = _randomPhoto

    fun getRandomPhoto() {
        _randomPhoto.postValue(Resource.Loading())
        repository.getRandomPhoto(30)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d("abobaperfection","Пришли данные в вью модель")
                _randomPhoto.postValue(Resource.Success(result))
            }, { exception ->
                _randomPhoto.postValue(Resource.Error(exception.message.toString(), null))
                Log.d("HomeViewModel","$exception")
            })
    }
}