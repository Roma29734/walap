package com.example.walap.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walap.databinding.CategoriesCardBinding

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: CategoriesCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var categoriesList = emptyList<String>()

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
        holder.binding.textNameCategories.text = categoriesList[position]
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun setCategories(list: List<String>) {
        categoriesList = list
        notifyDataSetChanged()
    }

}