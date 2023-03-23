package com.example.androidassignment.data.model


import com.google.gson.annotations.SerializedName

data class GenerateImageResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)