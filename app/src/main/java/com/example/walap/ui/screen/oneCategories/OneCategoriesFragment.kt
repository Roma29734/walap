package com.example.walap.ui.screen.oneCategories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walap.R
import com.example.walap.base.BaseFragment
import com.example.walap.data.toTransition
import com.example.walap.databinding.FragmentCategoriesBinding
import com.example.walap.databinding.FragmentOneCategoriesBinding
import com.example.walap.ui.adapter.WallpaperPagingAdapter
import com.example.walap.ui.screen.nav.NavFragmentDirections
import com.example.walap.utils.Resource

class OneCategoriesFragment :
    BaseFragment<FragmentOneCategoriesBinding>
        (FragmentOneCategoriesBinding::inflate) {

    private val args: OneCategoriesFragmentArgs by navArgs()
    private val viewModel: OneCategoriesViewModel by viewModels { viewModelFactory }
    private val adapter = WallpaperPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            mainRecycler.adapter = adapter
            mainRecycler.layoutManager = GridLayoutManager(context, 2)
            include.textView.text = args.name
            include.imgButtonBack.setOnClickListener { mainNavController.popBackStack() }
        }

        adapter.clickToImage = {
            val model = it.toTransition(it.urls.full)
            val action = OneCategoriesFragmentDirections.actionOneCategoriesFragmentToDetailFragment(model)
            mainNavController.navigate(action)
        }

        viewModel.getWallpaper(args.name)
        Log.d("homeAbobe", "отправил запрос")
        viewModel.oneCategoriesList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Log.d("homeAbobe", "зашел взагрузку")
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    Log.d("homeAbobe", "зашел в все збс")
                    binding.progressBar.visibility = View.INVISIBLE

                    it.data?.let { it1 -> adapter.submitData(lifecycle, it1) }
                }
            }
        }
    }
}