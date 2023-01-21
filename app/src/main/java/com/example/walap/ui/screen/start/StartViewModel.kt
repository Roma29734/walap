package com.example.walap.ui.screen.start


import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.example.walap.R
import com.example.walap.data.model.entity.CategoriesEntity
import com.example.walap.data.repository.WallpaperRepository
import com.example.walap.utils.resourceUri
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
        getUriImage(R.drawable.auto_categories),
        getUriImage(R.drawable.amoled_categories),
        getUriImage(R.drawable.animals_categories),
        getUriImage(R.drawable.abstract_categories),
        getUriImage(R.drawable.minimal_categories),
        getUriImage(R.drawable.nature_categories),
        getUriImage(R.drawable.sports_categories),
        getUriImage(R.drawable.shows_categories),
    )


    fun createCategoriesTable() {
        Observable.just(listCategories)
            .subscribeOn(Schedulers.io())
            .subscribeBy { result ->
                for(i in result.indices) {
                    val name = result[i]
                    val image = imageView[i]
                    val model = CategoriesEntity(0, name, image)
                    repository.insertCategories(model)
                }
            }
    }


    private fun getUriImage(image: Int): String {
        val path =
            Uri.parse("android.resource://com.example.walap/"
                    + image)

        return path.toString()
    }
}