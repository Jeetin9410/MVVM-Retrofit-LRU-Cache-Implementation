package com.example.androidassignment.data.repository


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidassignment.data.model.GenerateImageResponse
import com.example.androidassignment.network.ResultData
import home.network.ApiServices
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class GenerateImageRepository(var apiServices:ApiServices) {

    fun getImage( ) : LiveData<ResultData<GenerateImageResponse>> {
        val responseData: MutableLiveData<ResultData<GenerateImageResponse>> = MutableLiveData()
        responseData.postValue(ResultData.Loading())
        apiServices?.getDogImages()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<GenerateImageResponse> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(profileResult: GenerateImageResponse) {
                    responseData.postValue(ResultData.Success(profileResult))
                }

                override fun onError(e: Throwable) {
                    responseData.postValue(ResultData.Failed(errorMessage = "No Image Found"))
                }
            })
        return responseData
    }


}
