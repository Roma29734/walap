package com.example.walap.ui.screen.topPhoto

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walap.base.BaseFragment
import com.example.walap.data.toTransition
import com.example.walap.databinding.FragmentTopPhotoBinding
import com.example.walap.ui.adapter.WallpaperPagingAdapter
import com.example.walap.ui.screen.nav.NavFragmentDirections
import com.example.walap.utils.Resource

class TopPhotoFragment :
    BaseFragment<FragmentTopPhotoBinding>
        (FragmentTopPhotoBinding::inflate) {

    private val viewModel: TopPhotoViewModel by viewModels { viewModelFactory }
    private val pagerAdapter = WallpaperPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerAdapter.clickToImage = {
            val model = it.toTransition(it.urls.regular)
            val action = NavFragmentDirections.actionNavFragmentToDetailFragment(model)
            mainNavController.navigate(action)
        }

        binding.apply {
            mainRecycler.adapter = pagerAdapter
            mainRecycler.layoutManager = GridLayoutManager(context, 2)
            settingAdapter()
        }

        viewModel.getWallpaper()

        viewModel.movieList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {

                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE

                    it.data?.let { it1 -> pagerAdapter.submitData(lifecycle, it1) }
                }
                else -> {}
            }
        }
    }


    private fun settingAdapter() {
        pagerAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            ) {
                binding.progressBar.isVisible = true
            }else {
                binding.progressBar.isVisible = false
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(context, it.error.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
