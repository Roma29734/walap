package com.example.walap.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.walap.R
import com.example.walap.data.model.photoModelItem.PhotoModelItem
import com.example.walap.databinding.CardWallpaperPresentrBinding
import com.google.gson.annotations.Until

class WallpaperPagingAdapter : PagingDataAdapter<PhotoModelItem, WallpaperPagingAdapter.MyViewHolder>(
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
                    placeholder( R.drawable.ic_random )
                }
            }
            imageView.setOnClickListener {
                clickToImage?.let { it1 -> photo?.let { it2 -> it1(it2) } }
            }
        }

//        getItem(position)?.let {
//            holder.bind(it)
//        }
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

//class RepoListAdapter : PagingDataAdapter<Repository, RepoListAdapter.ViewHolder>(COMPARATOR) {
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val txtName: TextView = itemView.findViewById(R.id.txtName);
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val repo = getItem(position)
//        holder.txtName.text = repo?.full_name
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_view, parent, false)
//        return ViewHolder(view)
//    }
//
//    companion object {
//        private val COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {
//            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
//                oldItem.full_name == newItem.full_name
//
//            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
//                oldItem == newItem
//
//        }
//    }
//}