package com.example.androidassignment.ui.GenerateImageScreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.androidassignment.R
import com.example.androidassignment.network.ResultData
import com.example.androidassignment.ui.BaseApplication
import com.example.androidassignment.viewModel.GenerateImageViewModel
import kotlinx.android.synthetic.main.activity_generate_image.*
import kotlinx.coroutines.*
import java.net.URL


class GenerateImageActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(GenerateImageViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_image)
        var actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true);





        generateDogImagebtn.setOnClickListener {
           generateDogImagebtn.isEnabled = false
           getImages()
        }
    }

    private fun getImages() {
        viewModel.getDogImage().observe(this, Observer { resultData ->
            when (resultData) {
                is ResultData.Loading -> {

                }
                is ResultData.Success -> {
                    //Log.e("Jeet",resultData.data.games!!.size.toString())


                    Glide.with(this)
                        .load(resultData.data.message)
                        .thumbnail(0.05f)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(image);

                    generateDogImagebtn.isEnabled = true

                    GlobalScope.launch {
                        val imageBitmap = async { loadImageBitmap(URL(resultData.data.message)) }.await()
                        
                        BaseApplication.lruCache.put(resultData.data.message, imageBitmap)

                    }

                }
                is ResultData.Failed -> {
                    Toast.makeText(this, resultData.errorMessage, Toast.LENGTH_SHORT).show()
                    generateDogImagebtn.isEnabled = true
                }
            }
        })

    }

    companion object {
        private const val TAG = "MainActivity"
    }

    private suspend fun loadImageBitmap(url: URL): Bitmap = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}