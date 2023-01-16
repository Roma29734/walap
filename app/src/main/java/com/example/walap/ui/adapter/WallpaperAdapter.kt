package com.example.walap.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.walap.data.model.PhotoModel
import com.example.walap.databinding.CardWallpaperPresentrBinding

class WallpaperAdapter() : RecyclerView.Adapter<WallpaperAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: CardWallpaperPresentrBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var photoModels = PhotoModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardWallpaperPresentrBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val positionPhotoModel = photoModels[position]

        holder.binding.imageView.load(positionPhotoModel.urls.small)
        Log.d("abobaperfection","Установил $position")
    }

    override fun getItemCount(): Int {
        return photoModels.size
    }

    fun setWallpaper(list: PhotoModel) {
        photoModels = list
        notifyDataSetChanged()
    }
}