package com.example.androidassignment.ui

import android.app.Application
import android.graphics.Bitmap
import android.util.LruCache
import com.example.androidassignment.utils.Constant

class BaseApplication : Application() {

    @Synchronized
    fun getInstance(): BaseApplication {
        return mInstance
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        lruCache = LruCache(Constant.CACHE_CAPACITY)
    }

    companion object {

        lateinit var mInstance: BaseApplication
        lateinit var lruCache: LruCache<String, Bitmap>

    }
}