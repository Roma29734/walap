package com.example.walap.ui.screen.random

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walap.base.BaseFragment
import com.example.walap.data.toTransition
import com.example.walap.databinding.FragmentRandomBinding
import com.example.walap.ui.adapter.WallpaperAdapter
import com.example.walap.ui.screen.nav.NavFragmentDirections
import com.example.walap.utils.Resource


class RandomFragment :
    BaseFragment<FragmentRandomBinding>
        (FragmentRandomBinding::inflate) {

    private val viewModel: RandomViewModel by viewModels { viewModelFactory }
    private val adapterWallpaper = WallpaperAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterWallpaper.clickToImage = {
            val model = it.toTransition(it.urls.full)
            val action = NavFragmentDirections.actionNavFragmentToDetailFragment(model)
            mainNavController.navigate(action)
        }

        binding.apply {
            mainRecycler.adapter = adapterWallpaper
            mainRecycler.layoutManager = GridLayoutManager(context, 2)
        }

        viewModel.getRandomPhoto()
        viewModel.randomPhoto.observe(viewLifecycleOwner) {
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
                    it.data?.let { it1 -> adapterWallpaper.setWallpaper(it1) }
                }
            }
        }
    }
}