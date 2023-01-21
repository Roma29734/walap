package com.example.walap.ui.screen.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walap.data.model.entity.CategoriesEntity
import com.example.walap.data.repository.WallpaperRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val repository: WallpaperRepository
): ViewModel() {

    private var _categoriesModel: MutableLiveData<List<CategoriesEntity>> = MutableLiveData()
    val categoriesModel get() = _categoriesModel

    fun readCategoriesTable() {
        repository.readCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                _categoriesModel.postValue(it)
            }
    }

}