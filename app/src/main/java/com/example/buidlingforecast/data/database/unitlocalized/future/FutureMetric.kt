package com.example.buidlingforecast.data.database.unitlocalized.future

import androidx.room.ColumnInfo
import org.threeten.bp.LocalDate

class FutureMetric(
    @ColumnInfo(name = "date")
    override val localDate: LocalDate,
    @ColumnInfo(name = "avgtempC")
    override val avgTemperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIcon: String
) : UnitSpecificFutureWeatherEntry {
}