package com.example.buidlingforecast.data

import com.example.buidlingforecast.data.response.CurrentWeather
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "3d228798136c466eaaa125757180611"

//https://api.apixu.com/v1/current.json?q=london&lang=ar&key=3d228798136c466eaaa125757180611
interface ApixuService {

    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") language: String = "en"
    ): Deferred<CurrentWeather>

    companion object {

        operator fun invoke(): ApixuService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain
                    .request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)

            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder().client(okHttpClient).baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApixuService::class.java)
        }
    }

}