package com.example.buidlingforecast.data.network

import androidx.lifecycle.LiveData
import com.example.buidlingforecast.data.network.response.CurrentWeather

interface weatherNetworkOutsource {
    val downloadedWearherData : LiveData<CurrentWeather>

    suspend fun fetchCurrentWeather(location:String,lang:String)

}