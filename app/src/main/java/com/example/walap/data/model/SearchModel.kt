package com.example.walap.data.model

import com.example.walap.data.model.photoModelItem.PhotoModelItem

data class SearchModel(
    val total: Int,
    val total_pages: Int,
    val results: ArrayList<PhotoModelItem>
)