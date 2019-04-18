package com.example.buidlingforecast.data.database.unitlocalized.future.detail

import org.threeten.bp.LocalDate

interface UnitDetailSpecific {
    val localDate: LocalDate
    val maxTemperature: Double
    val minTemperature: Double
    val avgTemperature: Double
    val conditionText: String
    val conditionIcon: String
    val maxWindSpeed: Double
    val totalPercipatition: Double
    val avgVisibility: Double
    val uv: Double
}