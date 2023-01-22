package com.example.walap.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.walap.ui.screen.random.RandomFragment
import com.example.walap.ui.screen.topPhoto.TopPhotoFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> TopPhotoFragment()
            else -> {
                RandomFragment()
            }
        }
    }
}