package com.example.walap.utils

sealed class DetailState<T>(
    val result: String? = null
) {
    class Success<T>(data: String) : DetailState<T>(data)
    class Error<T>(message: String) : DetailState<T>(message)
    class Loading<T> : DetailState<T>()
}