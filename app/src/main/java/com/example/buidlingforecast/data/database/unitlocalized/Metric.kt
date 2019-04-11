package com.example.buidlingforecast.data.database.unitlocalized

import androidx.room.ColumnInfo

data class Metric(
    @ColumnInfo(name = "tempC")
    override val tempreature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionImgUrl: String,
    @ColumnInfo(name = "windKph")
    override val windSpeed: Double,
    @ColumnInfo(name = "windDir")
    override val windDirection: String,
    @ColumnInfo(name = "precipMm")
    override val precipationVolume: Double,
    @ColumnInfo(name = "feelslikeC")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name = "visKm")
    override val visibilityDistance: Double
) : unitSpecificCurrentWeatherEntry