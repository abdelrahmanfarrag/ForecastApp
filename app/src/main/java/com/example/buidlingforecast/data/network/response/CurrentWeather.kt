package com.example.buidlingforecast.data.network.response

import androidx.room.Entity
import com.example.buidlingforecast.data.database.entity.CurrentWeatherEntity
import com.example.buidlingforecast.data.database.entity.Location
import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("current")
    val current: CurrentWeatherEntity,
    @SerializedName("location")
    val location: Location
) {
        data class Condition(
            @SerializedName("code")
            val code: Int,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("text")
            val text: String
        )
    }

