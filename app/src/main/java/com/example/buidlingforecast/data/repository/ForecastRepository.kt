package com.example.buidlingforecast.data.repository

import androidx.lifecycle.LiveData
import com.example.buidlingforecast.data.database.unitlocalized.unitSpecificCurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather(isMetric : Boolean) :LiveData<out unitSpecificCurrentWeatherEntry>
}