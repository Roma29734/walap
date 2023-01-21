package com.example.walap.ui.screen.start

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.walap.R
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentStartBinding
import com.example.walap.utils.resourceUri


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

        context?.let { resourceUri(it, R.drawable.abstract_categories) }
    }
}