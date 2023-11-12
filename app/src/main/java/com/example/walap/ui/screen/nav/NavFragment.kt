package com.example.walap.ui.screen.nav

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.walap.R
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentNavBinding

class  NavFragment :
    BaseFragment<FragmentNavBinding>
        (FragmentNavBinding::inflate) {

    private val viewModel: NavViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView = binding.bottomNavigationView
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        val navController = navHostFragment.findNavController()

        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getSizeTable()
        viewModel.stateCreateTable.observe(viewLifecycleOwner) {
            if (it != true) {
                mainNavController.navigate(R.id.action_navFragment_to_startFragment)
            }
        }
    }
}