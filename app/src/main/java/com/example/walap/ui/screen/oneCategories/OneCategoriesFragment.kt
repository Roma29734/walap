package com.example.walap.ui.screen.oneCategories

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walap.base.BaseFragment
import com.example.walap.data.toTransition
import com.example.walap.databinding.FragmentOneCategoriesBinding
import com.example.walap.ui.adapter.WallpaperPagingAdapter
import com.example.walap.utils.Resource

class OneCategoriesFragment :
    BaseFragment<FragmentOneCategoriesBinding>
        (FragmentOneCategoriesBinding::inflate) {

    private val args: OneCategoriesFragmentArgs by navArgs()
    private val viewModel: OneCategoriesViewModel by viewModels { viewModelFactory }
    private val adapter = WallpaperPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingAdapter()
        binding.apply {
            mainRecycler.adapter = adapter
            mainRecycler.layoutManager = GridLayoutManager(context, 2)
            include.textView.text = args.name
            include.imgButtonBack.setOnClickListener { mainNavController.popBackStack() }
        }

        adapter.clickToImage = {
            val model = it.toTransition(it.urls.full)
            val action =
                OneCategoriesFragmentDirections.actionOneCategoriesFragmentToDetailFragment(model)
            mainNavController.navigate(action)
        }

        viewModel.getWallpaper(args.name)
        Log.d("homeAbobe", "отправил запрос")
        viewModel.oneCategoriesList.observe(viewLifecycleOwner) {
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

                    it.data?.let { it1 -> adapter.submitData(lifecycle, it1) }
                }
            }
        }
    }

    private fun settingAdapter() {
        adapter.addLoadStateListener { loadState ->

//

            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            ) {
                binding.progressBar.isVisible = true
            } else {
                binding.progressBar.isVisible = false
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