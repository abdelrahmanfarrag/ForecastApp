package com.example.buidlingforecast.data.database.unitlocalized

interface unitSpecificCurrentWeatherEntry {
    val tempreature: Double
    val conditionText: String
    val conditionImgUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}