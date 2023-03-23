package com.example.androidassignment.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.androidassignment.data.model.GenerateImageResponse
import com.example.androidassignment.data.repository.GenerateImageRepository
import com.example.androidassignment.network.ResultData
import home.network.getAPIService

class GenerateImageViewModel(app: Application) : AndroidViewModel(app) {
    private val myrepo = GenerateImageRepository(getAPIService(app.applicationContext)!!)

    fun getDogImage() : LiveData<ResultData<GenerateImageResponse>> {
        return myrepo.getImage()
    }
}