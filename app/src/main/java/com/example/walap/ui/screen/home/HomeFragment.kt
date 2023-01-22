package com.example.walap.ui.screen.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walap.base.BaseFragment
import com.example.walap.data.toTransition
import com.example.walap.databinding.FragmentHomeBinding
import com.example.walap.ui.adapter.ViewPagerAdapter
import com.example.walap.ui.adapter.WallpaperAdapter
import com.example.walap.ui.adapter.WallpaperPagingAdapter
import com.example.walap.ui.screen.nav.NavFragmentDirections
import com.example.walap.utils.InternetConnection
import com.example.walap.utils.Resource
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private var contex: Context?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contex = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            upBar.textView.text = "Walap"
            viewPager.adapter = ViewPagerAdapter(contex as FragmentActivity)
            setingsTab()
        }
    }

    private fun setingsTab() {
        view?.let {
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
                when(pos) {
                    0 -> {
                        tab.text = "TopPhoto"
                    }
                    1 -> {
                        tab.text = "Random"
                    }
                }
            }.attach()
        }
    }
}