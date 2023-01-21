package com.example.walap.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.walap.data.model.entity.CategoriesEntity
import com.example.walap.databinding.CategoriesCardBinding


class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: CategoriesCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var categoriesList = emptyList<CategoriesEntity>()

    var clickToImage: ((nameCategory: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CategoriesCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val positionCategory = categoriesList[position]

        holder.binding.apply {
            textNameCategories.text = positionCategory.name
            cardView.setOnClickListener { clickToImage?.let { it1 -> it1(positionCategory.name) } }

            imageView3.load(positionCategory.imageView)
        }

    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun setCategories(list: List<CategoriesEntity>) {
        categoriesList = list
        notifyDataSetChanged()
    }

}