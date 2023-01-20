package com.example.walap.ui.screen.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.mainImageView.load(args.photo.url)
    }
}