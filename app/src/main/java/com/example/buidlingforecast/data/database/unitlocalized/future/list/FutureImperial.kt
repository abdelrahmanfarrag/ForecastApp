package com.example.buidlingforecast.data.database.unitlocalized.future.list

import androidx.room.ColumnInfo
import org.threeten.bp.LocalDate

class FutureImperial(
    @ColumnInfo(name = "date")
    override val localDate: LocalDate,
    @ColumnInfo(name = "avgtempF")
    override val avgTemperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIcon: String
) : UnitSpecificFutureWeatherEntry {
}