@file:Suppress("SpellCheckingInspection")

package com.example.buidlingforecast.data.network

import com.example.buidlingforecast.data.network.response.CurrentWeather
import com.example.buidlingforecast.data.network.response.FutureResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "3d228798136c466eaaa125757180611"
const val BASE_URL = "https://api.apixu.com/v1/"
const val CURRENT_ENDPOINT = "current.json"
const val FORECAST_ENDPOINT = "forecast.json"
const val DEFAULT_LANGUAGE = "en"
const val LOCATION = "q"
const val LANGUAGE = "lang"
const val DAYS = "days"
const val KEY = "key"

//current => https://api.apixu.com/v1/current.json?q=london&lang=ar&key=3d228798136c466eaaa125757180611
//future =>http://api.apixu.com/v1/forecast.json?key=3d228798136c466eaaa125757180611&q=cairo&days=1
interface ApixuService {

    @GET(CURRENT_ENDPOINT)
    fun getCurrentWeatherAsync(
        @Query(LOCATION) location: String,
        @Query(LANGUAGE) language: String = DEFAULT_LANGUAGE
    ): Deferred<CurrentWeather>

    @GET(FORECAST_ENDPOINT)
    fun getFutureWeatherAsync(
        @Query(LOCATION) location: String,
        @Query(DAYS) numOfDays: Int,
        @Query(LANGUAGE) language: String = DEFAULT_LANGUAGE
    ): Deferred<FutureResponse>


    companion object {

        operator fun invoke(
            ConnectivityInterceptor: ConnectivityInterceptor,
            loggingInterceptor: LoggingInterceptor

        ): ApixuService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain
                    .request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(KEY, API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)

            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(ConnectivityInterceptor)
                .addInterceptor(loggingInterceptor.addingLoggingInterceptor())
                .build()

            return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApixuService::class.java)
        }
    }

}