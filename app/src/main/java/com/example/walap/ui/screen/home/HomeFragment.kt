package com.example.walap.ui.screen.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentHomeBinding
import com.example.walap.ui.adapter.WallpaperAdapter

class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private val adapterWallpaper = WallpaperAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRecycler.adapter = adapterWallpaper
        binding.mainRecycler.layoutManager = GridLayoutManager(context, 2)

        viewModel.getWallpaper()
        Log.d("abobaperfection","Отправил запрос на получение данных")
        viewModel.photoTop.observe(viewLifecycleOwner) {result ->
            Log.d("abobaperfection","Установил данные в адаптер")
            adapterWallpaper.setWallpaper(result)
        }

    }
}