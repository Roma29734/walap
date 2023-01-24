package com.example.walap.ui.screen.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.walap.R
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentStartBinding


class StartFragment :
    BaseFragment<FragmentStartBinding>
        (FragmentStartBinding::inflate) {

    private val viewModel: StartViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.materialButton.setOnClickListener {
            viewModel.createCategoriesTable()
            mainNavController.navigate(R.id.action_startFragment_to_navFragment)
        }

    }

}