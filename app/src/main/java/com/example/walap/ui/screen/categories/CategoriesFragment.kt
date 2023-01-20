package com.example.walap.ui.screen.categories

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentCategoriesBinding
import com.example.walap.ui.adapter.CategoriesAdapter
import com.example.walap.utils.InternetConnection
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.jvm.Throws


class CategoriesFragment :
    BaseFragment<FragmentCategoriesBinding>
        (FragmentCategoriesBinding::inflate) {

    private val adapter = CategoriesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listCategories =
            listOf(
                "Auto",
                "Amoled",
                "Animals",
                "Abstract",
                "Games",
                "Minimal",
                "Nature",
                "Sports",
                "Shows"
            )

        binding.mainRecyclerView.adapter = adapter
        adapter.setCategories(listCategories)
    }
}