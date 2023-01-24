package com.example.walap.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.walap.R
import com.example.walap.data.model.photoModelItem.PhotoModelItem
import com.example.walap.databinding.CardWallpaperPresentrBinding

class WallpaperPagingAdapter :
    PagingDataAdapter<PhotoModelItem, WallpaperPagingAdapter.MyViewHolder>(
        COMPARATOR
    ) {
    inner class MyViewHolder(val binding: CardWallpaperPresentrBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoModelItem) {
            binding.apply {
                imageView.load(photo.urls.small) {
                    placeholder(R.drawable.ic_random)
                }
                imageView.setOnClickListener {
                    clickToImage?.let { it1 -> it1(photo) }
                }
            }
        }
    }

    var clickToImage: ((photo: PhotoModelItem) -> Unit)? = null

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val photo = getItem(position)
        holder.binding.apply {
            if (photo != null) {
                imageView.load(photo.urls.small) {
                    listener(onStart = {
                        holder.binding.progressBar.isVisible = true
                    }, onSuccess = { _, _ ->
                        holder.binding.progressBar.isVisible = false
                    })
                    placeholder(R.drawable.ic_plug)
                }
            }
            imageView.setOnClickListener {
                clickToImage?.let { it1 -> photo?.let { it2 -> it1(it2) } }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardWallpaperPresentrBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    object COMPARATOR : DiffUtil.ItemCallback<PhotoModelItem>() {
        override fun areItemsTheSame(oldItem: PhotoModelItem, newItem: PhotoModelItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoModelItem, newItem: PhotoModelItem): Boolean {
            return oldItem == newItem
        }
    }
}
