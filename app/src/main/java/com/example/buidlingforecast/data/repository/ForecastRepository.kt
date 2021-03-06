package com.example.buidlingforecast.data.repository

import androidx.lifecycle.LiveData
import com.example.buidlingforecast.data.database.entity.Location
import com.example.buidlingforecast.data.database.unitlocalized.current.unitSpecificCurrentWeatherEntry
import com.example.buidlingforecast.data.database.unitlocalized.future.detail.UnitDetailSpecific
import com.example.buidlingforecast.data.database.unitlocalized.future.list.UnitSpecificFutureWeatherEntry
import org.threeten.bp.LocalDate

interface ForecastRepository {

    suspend fun getCurrentWeather(isMetric: Boolean): LiveData<out unitSpecificCurrentWeatherEntry>

    suspend fun getWeatherLocation(): LiveData<Location>

    suspend fun getFutureWeather(
        startDate: LocalDate,
        isMetric: Boolean
    ): LiveData<out List<UnitSpecificFutureWeatherEntry>>
    suspend fun getDetailedFutureWeather(isMetric: Boolean,date: LocalDate):LiveData<out UnitDetailSpecific>

}