package com.example.buidlingforecast.data.database.unitlocalized.current

import androidx.room.ColumnInfo

data class Imperial(
    @ColumnInfo(name = "tempF")
    override val tempreature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionImgUrl: String,
    @ColumnInfo(name = "windMph")
    override val windSpeed: Double,
    @ColumnInfo(name = "windDir")
    override val windDirection: String,
    @ColumnInfo(name = "pressureIn")
    override val precipationVolume: Double,
    @ColumnInfo(name = "feelslikeF")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name = "visMiles")
    override val visibilityDistance: Double
) : unitSpecificCurrentWeatherEntry