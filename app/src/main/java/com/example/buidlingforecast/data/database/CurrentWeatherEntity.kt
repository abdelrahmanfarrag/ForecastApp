package com.example.buidlingforecast.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.buidlingforecast.data.response.CurrentWeather
import com.google.gson.annotations.SerializedName

const val currentWeathId = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @Embedded(prefix = "current_")
    val current: CurrentWeather.Current,
    @Embedded(prefix = "location_")
    val location: CurrentWeather.Location
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = currentWeathId
}