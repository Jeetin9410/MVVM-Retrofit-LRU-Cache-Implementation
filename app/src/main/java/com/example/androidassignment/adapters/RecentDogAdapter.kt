package com.example.androidassignment.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.R

class RecentDogAdapter(private var dogImageList: List<Bitmap>): RecyclerView.Adapter<RecentDogAdapter.MyViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dog, parent, false)
        return MyViewHolder(itemView)
    }
    

    override fun getItemCount() = dogImageList.size


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: ImageView = view.findViewById(R.id.imgDog)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dogImageList[position]
        holder.itemTextView.setImageBitmap(item)
    }

    


}