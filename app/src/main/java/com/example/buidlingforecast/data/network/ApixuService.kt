package com.example.buidlingforecast.data.network

import com.example.buidlingforecast.data.network.response.CurrentWeather
import com.example.buidlingforecast.data.network.response.FutureResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "3d228798136c466eaaa125757180611"

//current => https://api.apixu.com/v1/current.json?q=london&lang=ar&key=3d228798136c466eaaa125757180611
//future =>http://api.apixu.com/v1/forecast.json?key=3d228798136c466eaaa125757180611&q=cairo&days=1
interface ApixuService {

    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") language: String = "en"
    ): Deferred<CurrentWeather>

    @GET("forecast.json")
    fun getFutureWeather(
        @Query("q") location: String,
        @Query("days") numOfDays: Int,
        @Query("lang") language: String = "en"
    ): Deferred<FutureResponse>


    companion object {

        operator fun invoke(
            connectivityInterceptor: connectivityInterceptor

        ): ApixuService {
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
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder().client(okHttpClient).baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApixuService::class.java)
        }
    }

}