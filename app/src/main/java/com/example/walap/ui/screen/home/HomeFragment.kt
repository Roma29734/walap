package com.example.walap.ui.screen.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walap.base.BaseFragment
import com.example.walap.data.toTransition
import com.example.walap.databinding.FragmentHomeBinding
import com.example.walap.ui.adapter.WallpaperAdapter
import com.example.walap.ui.adapter.WallpaperPagingAdapter
import com.example.walap.ui.screen.nav.NavFragmentDirections
import com.example.walap.utils.InternetConnection
import com.example.walap.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private val pagerAdapter = WallpaperPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerAdapter.clickToImage = {
            val model = it.toTransition(it.urls.full)
            val action = NavFragmentDirections.actionNavFragmentToDetailFragment(model)
            mainNavController.navigate(action)
        }

        binding.apply {
            upBar.textView.text = "New"
            mainRecycler.adapter = pagerAdapter
            mainRecycler.layoutManager = GridLayoutManager(context, 2)
        }

        viewModel.getWallpaper()
        Log.d("homeAbobe", "отправил запрос")
        viewModel.movieList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Log.d("homeAbobe", "зашел взагрузку")
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    Log.d("homeAbobe", "зашел в все збс")
                    binding.progressBar.visibility = View.INVISIBLE

                    it.data?.let { it1 -> pagerAdapter.submitData(lifecycle, it1) }
                }
                else -> {}
            }

        }

    }
}