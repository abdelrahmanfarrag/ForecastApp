package com.example.buidlingforecast.data.network.response

import com.example.buidlingforecast.data.database.entity.FutureWeatherEntry
import com.google.gson.annotations.SerializedName

data class ForecastDaysContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)