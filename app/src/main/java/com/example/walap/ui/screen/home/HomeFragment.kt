package com.example.walap.ui.screen.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentHomeBinding
import com.example.walap.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private var contex: Context? = null

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
                when (pos) {
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