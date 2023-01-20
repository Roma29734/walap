package com.example.walap.data

import com.example.walap.data.model.TransitionPhotoModel
import com.example.walap.data.model.photoModelItem.PhotoModelItem

fun PhotoModelItem.toTransition(urls: String) = TransitionPhotoModel(
    url = urls,
    likes = likes,
)