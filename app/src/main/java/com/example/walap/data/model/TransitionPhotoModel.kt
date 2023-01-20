package com.example.walap.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransitionPhotoModel(
    val url: String,
    val likes: Int,
): Parcelable