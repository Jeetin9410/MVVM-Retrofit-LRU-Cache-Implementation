package home.network

import com.example.androidassignment.data.model.GenerateImageResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    @GET("image/random")
    fun getDogImages() : Observable<GenerateImageResponse>

}