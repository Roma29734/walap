package com.example.walap.ui.screen.nav

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.walap.R
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentNavBinding

class NavFragment :
    BaseFragment<FragmentNavBinding>
        (FragmentNavBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView = binding.bottomNavigationView
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        val navController = navHostFragment.findNavController()

        navView.setupWithNavController(navController)
    }
}