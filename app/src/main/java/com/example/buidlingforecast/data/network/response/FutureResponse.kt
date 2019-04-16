package com.example.buidlingforecast.data.network.response

import com.example.buidlingforecast.data.database.entity.Condition
import com.example.buidlingforecast.data.database.entity.Location
import com.google.gson.annotations.SerializedName

data class FutureResponse(
    @SerializedName("forecast")
    val forecastEntries: ForecastDaysContainer,
    @SerializedName("location")
    val location: Location
)