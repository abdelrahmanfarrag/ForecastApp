package com.example.buidlingforecast.data.database.unitlocalized.future.list

import org.threeten.bp.LocalDate

interface UnitSpecificFutureWeatherEntry {
    val localDate:LocalDate
    val avgTemperature:Double
    val conditionText:String
    val conditionIcon:String
}