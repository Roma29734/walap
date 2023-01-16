package com.example.walap.ui.screen.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentHomeBinding
import com.example.walap.ui.adapter.WallpaperAdapter
import com.example.walap.ui.adapter.WallpaperPagingAdapter
import com.example.walap.utils.Resource

class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private val adapterWallpaper = WallpaperAdapter()
    private val pagerAdapter = WallpaperPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            upBar.textView.text = "New"
            mainRecycler.adapter = pagerAdapter
            mainRecycler.layoutManager = GridLayoutManager(context, 2)
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
            }

        }



//        Log.d("abobaperfection","Отправил запрос на получение данных")
//        viewModel.photoTop.observe(viewLifecycleOwner) {
//            when(it) {
//                is Resource.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                }
//                is Resource.Error -> {
//                    binding.progressBar.visibility = View.INVISIBLE
//                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
//                }
//                is Resource.Success -> {
//                    binding.progressBar.visibility = View.INVISIBLE
//                    it.data?.let { it1 -> adapterWallpaper.setWallpaper(it1) }
//                }
//            }
//        }
    }
}