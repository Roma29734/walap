package com.example.walap.utils

sealed class ResultRepo<T>(
    val data: T? = null,
    val message: Throwable? = null
) {
    class Success<T>(data: T) : ResultRepo<T>(data)
    class Error<T>(message: Throwable, data: T? = null) : ResultRepo<T>(data, message)
}
