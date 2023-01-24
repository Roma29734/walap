package com.example.walap.ui.screen.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.walap.data.model.entity.CategoriesEntity
import com.example.walap.data.repository.WallpaperRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class StartViewModel @Inject constructor(
    application: Application,
    private val repository: WallpaperRepository
) : AndroidViewModel(application) {

    private val context = application

    private val listCategories =
        listOf(
            "Auto",
            "Amoled",
            "Animals",
            "Abstract",
            "Minimal",
            "Nature",
            "Sports",
            "Shows"
        )

    private val imageView = listOf<String>(
        getUriImage("auto_categories"),
        getUriImage("amoled_categories"),
        getUriImage("animals_categories"),
        getUriImage("abstract_categories"),
        getUriImage("minimal_categories"),
        getUriImage("nature_categories"),
        getUriImage("sports_categories"),
        getUriImage("shows_categories"),
    )


    fun createCategoriesTable() {
        Observable.just(listCategories)
            .subscribeOn(Schedulers.io())
            .subscribeBy { result ->
                for (i in result.indices) {
                    val name = result[i]
                    val image = imageView[i]
                    val model = CategoriesEntity(0, name, image)
                    repository.insertCategories(model)
                }
            }
    }


    private fun getUriImage(image: String): String {
        return "android.resource://com.example.walap/drawable/$image"
    }
}