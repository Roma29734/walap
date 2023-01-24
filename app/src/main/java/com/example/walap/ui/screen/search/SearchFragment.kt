package com.example.walap.ui.screen.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walap.base.BaseFragment
import com.example.walap.data.toTransition
import com.example.walap.databinding.FragmentSearchBinding
import com.example.walap.ui.adapter.WallpaperPagingAdapter
import com.example.walap.ui.screen.nav.NavFragmentDirections
import com.example.walap.utils.Resource

class SearchFragment :
    BaseFragment<FragmentSearchBinding>
        (FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }
    private val adapter = WallpaperPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.clickToImage = {
            val model = it.toTransition(it.urls.full)
            val action = NavFragmentDirections.actionNavFragmentToDetailFragment(model)
            mainNavController.navigate(action)
        }

//        Настройка view
        settingAdapter()
        binding.apply {
//            Настройка ресайкла
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(context, 2)

//            Настройка searchView

            SearchView.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0 != null) {
                        if (p0.isNotEmpty()) p0.let { viewModel.searchWallpaper(p0) }
                    }
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null) {
                        if (p0.isNotEmpty()) p0.let { viewModel.searchWallpaper(p0) }
                    }
                    return false
                }
            })
        }

//        Получение результата поисков
        viewModel.searchWallpaperResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(context, "${result.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE

                    result.data?.let { it1 -> adapter.submitData(lifecycle, it1) }
                }
            }
        }
    }

    private fun settingAdapter() {
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            ) {
            } else {

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(context, it.error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}