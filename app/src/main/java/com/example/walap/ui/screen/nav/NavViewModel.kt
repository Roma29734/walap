package com.example.walap.ui.screen.nav

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walap.data.repository.WallpaperRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NavViewModel @Inject constructor(
    private val repository: WallpaperRepository
): ViewModel() {


    private var _stateCreateTable: MutableLiveData<Boolean> = MutableLiveData()
    val stateCreateTable get() = _stateCreateTable

    fun getSizeTable() {
        repository.getSizeCategoriesTable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (onSuccess = { result ->
                _stateCreateTable.postValue(result != 0)
            }, onError = {

            })
    }
}