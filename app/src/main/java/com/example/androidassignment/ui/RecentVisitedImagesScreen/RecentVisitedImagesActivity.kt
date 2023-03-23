package com.example.androidassignment.ui.RecentVisitedImagesScreen

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.R
import com.example.androidassignment.adapters.RecentDogAdapter
import com.example.androidassignment.ui.BaseApplication
import kotlinx.android.synthetic.main.activity_recent_visited_images.*

class RecentVisitedImagesActivity : AppCompatActivity() {
    private var allDogs : Map<String,Bitmap> = mutableMapOf()
    private lateinit var recentDogAdapter: RecentDogAdapter
    private var dogImages = mutableListOf<Bitmap>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_visited_images)
        var actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true);



        retrieveDogImages()
        setUpRecyclerView()

        clearCacheBtn.setOnClickListener {
            BaseApplication.lruCache.evictAll()
            dogImages.clear()
            recentDogAdapter.notifyDataSetChanged()
        }




    }
    fun retrieveDogImages(){
        allDogs =  BaseApplication.lruCache.snapshot()

        allDogs.forEach { (key, value) ->
            dogImages.add(value)
        }
        dogImages.reverse()
    }

    fun setUpRecyclerView(){
        val recyclerView: RecyclerView = findViewById(R.id.rvRecentVisitedDogs)
        recentDogAdapter = RecentDogAdapter(dogImages)
        recyclerView.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        recyclerView.adapter = recentDogAdapter
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