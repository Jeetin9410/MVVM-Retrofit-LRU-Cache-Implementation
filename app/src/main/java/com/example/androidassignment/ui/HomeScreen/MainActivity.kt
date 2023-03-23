package com.example.androidassignment.ui.HomeScreen

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidassignment.R
import com.example.androidassignment.ui.GenerateImageScreen.GenerateImageActivity
import com.example.androidassignment.ui.RecentVisitedImagesScreen.RecentVisitedImagesActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        generateDogImage.setOnClickListener {
            startActivity(Intent(this, GenerateImageActivity::class.java))
        }

        recentlyVisited.setOnClickListener{
            startActivity(Intent(this, RecentVisitedImagesActivity::class.java))
        }

    }
}