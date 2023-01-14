package com.example.walap.ui.screen.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walap.data.model.PhotoModel
import com.example.walap.data.repository.WallpaperRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: WallpaperRepository
): ViewModel() {

    private val _photoTop: MutableLiveData<PhotoModel> = MutableLiveData()
    val photoTop get() = _photoTop

    fun getWallpaper() {
        repository.getTopPhoto(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d("abobaperfection","Пришли данные в вью модель")
                _photoTop.postValue(result)
            }, {
                Log.d("HomeViewModel","$it")
            })
    }

}