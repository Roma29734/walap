package com.example.walap.ui.screen.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentCategoriesBinding
import com.example.walap.ui.adapter.CategoriesAdapter
import com.example.walap.ui.screen.nav.NavFragmentDirections
import java.io.InputStream





class CategoriesFragment :
    BaseFragment<FragmentCategoriesBinding>
        (FragmentCategoriesBinding::inflate) {

    private val viewModel: CategoriesViewModel by viewModels { viewModelFactory }
    private val adapter = CategoriesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRecyclerView.adapter = adapter

        adapter.clickToImage = {
            val action = NavFragmentDirections.actionNavFragmentToOneCategoriesFragment(it)
            mainNavController.navigate(action)
        }

        viewModel.readCategoriesTable()
        viewModel.categoriesModel.observe(viewLifecycleOwner) {
            adapter.setCategories(it)
        }


    }
}