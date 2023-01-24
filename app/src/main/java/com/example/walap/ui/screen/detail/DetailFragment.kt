package com.example.walap.ui.screen.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.walap.R
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentDetailBinding

class DetailFragment :
    BaseFragment<FragmentDetailBinding>
        (FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            mainImageView.load(args.photo.url) {
                listener(onStart = {
                    binding.progressBar.isVisible = true
                }, onSuccess = { _, _ ->
                    binding.progressBar.isVisible = false
                })
                placeholder( R.drawable.ic_random )
            }
            upBar.imageButtonBack.setOnClickListener { mainNavController.popBackStack() }
        }
    }
}