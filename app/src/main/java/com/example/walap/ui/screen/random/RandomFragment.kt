package com.example.walap.ui.screen.random

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walap.R
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentRandomBinding
import com.example.walap.ui.adapter.WallpaperAdapter
import com.example.walap.ui.screen.home.HomeViewModel
import com.example.walap.utils.Resource


class RandomFragment :
    BaseFragment<FragmentRandomBinding>
        (FragmentRandomBinding::inflate) {

    private val viewModel: RandomViewModel by viewModels { viewModelFactory }
    private val adapterWallpaper = WallpaperAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            upBar.textView.text = "Random"
            mainRecycler.adapter = adapterWallpaper
            mainRecycler.layoutManager = GridLayoutManager(context, 2)
        }

        viewModel.getRandomPhoto()
        viewModel.randomPhoto.observe(viewLifecycleOwner) {
            when(it) {
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