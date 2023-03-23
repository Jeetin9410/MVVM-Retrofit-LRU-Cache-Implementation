package com.example.androidassignment.network

sealed class ResultData<out T> {
    data class Loading(val nothing: Nothing? = null): ResultData<Nothing>()
    data class Success<out T>(val data: T): ResultData<T>()
    data class Failed(val errorMessage: String? = null, val exception: Exception? = null,
                      val throwable: Throwable? = null): ResultData<Nothing>()
}