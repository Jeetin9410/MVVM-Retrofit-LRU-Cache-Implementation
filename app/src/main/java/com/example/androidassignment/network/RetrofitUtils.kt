package home.network

import android.content.Context
import com.example.androidassignment.utils.Constant
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitUtils {

    private var retrofit: Retrofit? = null
    private var apiServices: ApiServices? = null

    fun getClient(context: Context): Retrofit {

        val loggingInterceptor =  HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val okHttpClient =  OkHttpClient.Builder()
            .addInterceptor(object: Interceptor {


                override fun intercept(chain: Interceptor.Chain): Response {
                    val originalRequest = chain.request();

                    val newRequest: Any

                            newRequest = originalRequest.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build();


                    return chain.proceed(newRequest);
                }
            })
            .addInterceptor(loggingInterceptor)
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(Constant.DOG_API_BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
        return retrofit!!
    }

//

    fun createRetrofitApi(context: Context): ApiServices? {
        return if (retrofit == null) {
            getClient(context).create(ApiServices::class.java)
        } else {
            retrofit?.create(ApiServices::class.java)
        }
    }

    fun createApiService(context: Context): ApiServices? {
        return if (apiServices == null) {
            apiServices = createRetrofitApi(context)
            apiServices
        } else {
            apiServices
        }
    }

}
fun  getAPIService(context: Context) : ApiServices? {
    return RetrofitUtils.createApiService(context)
}