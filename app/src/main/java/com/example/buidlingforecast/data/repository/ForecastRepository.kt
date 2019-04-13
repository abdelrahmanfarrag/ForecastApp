package com.example.buidlingforecast.data.repository

import androidx.lifecycle.LiveData
import com.example.buidlingforecast.data.database.entity.Location
import com.example.buidlingforecast.data.database.unitlocalized.unitSpecificCurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather(isMetric : Boolean) :LiveData<out unitSpecificCurrentWeatherEntry>

    suspend fun getWeatherLocation():LiveData<Location>
}