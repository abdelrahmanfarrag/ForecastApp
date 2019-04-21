package com.example.buidlingforecast.data.network

import androidx.lifecycle.LiveData
import com.example.buidlingforecast.data.network.response.CurrentWeather
import com.example.buidlingforecast.data.network.response.FutureResponse

interface WeatherNetworkOutsource {

    val downloadedWeatherData: LiveData<CurrentWeather>
    val downloadedFutureWeatherData: LiveData<FutureResponse>

    suspend fun fetchCurrentWeather(location: String, lang: String)
    suspend fun fetchFutureWeather(location: String, lang: String, days: Int)

}