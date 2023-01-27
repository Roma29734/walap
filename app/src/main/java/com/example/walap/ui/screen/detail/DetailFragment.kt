package com.example.walap.ui.screen.detail

import android.os.*
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.walap.R
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentDetailBinding
import com.example.walap.ui.view.LoadingDialog
import com.example.walap.utils.DetailState

class DetailFragment :
    BaseFragment<FragmentDetailBinding>
        (FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels { viewModelFactory }
    private val loadingDialog by lazy { LoadingDialog(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            mainImageView.load(args.photo.url) {
                listener(onStart = {
                    progressBar.isVisible = true
                }, onSuccess = { _, _ ->
                    progressBar.isVisible = false
                })
                placeholder(R.drawable.ic_random)
            }
            upBar.imageButtonBack.setOnClickListener { mainNavController.popBackStack() }

            imgButtonDownload.setOnClickListener {
                viewModel.downloadWallpapers(args.photo.urlDownload)
            }

            imgButtonSetWallpaper.setOnClickListener {

                viewModel.setWallpapers(args.photo.urlDownload)
            }
        }

        viewModel.downloadState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DetailState.Loading -> {
                    loadingDialog.startLoading()
                }
                is DetailState.Error -> {
                    loadingDialog.isDismiss()
                    Toast.makeText(context, "${result.result}", Toast.LENGTH_SHORT).show()
                }
                is DetailState.Success -> {
                    loadingDialog.isDismiss()
                    Toast.makeText(context, "${result.result}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}