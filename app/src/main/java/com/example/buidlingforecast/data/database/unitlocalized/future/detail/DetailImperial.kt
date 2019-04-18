package com.example.buidlingforecast.data.database.unitlocalized.future.detail

import androidx.room.ColumnInfo
import org.threeten.bp.LocalDate

class DetailImperial(
    @ColumnInfo(name = "date")
    override val localDate: LocalDate,
    @ColumnInfo(name = "maxtempF")
    override val maxTemperature: Double,
    @ColumnInfo(name = "mintempF")
    override val minTemperature: Double,
    @ColumnInfo(name = "avgtempF")
    override val avgTemperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIcon: String,
    @ColumnInfo(name = "maxwindMph")
    override val maxWindSpeed: Double,
    @ColumnInfo(name = "totalprecipIn")
    override val totalPercipatition: Double,
    @ColumnInfo(name = "avgvisMiles")
    override val avgVisibility: Double,
    @ColumnInfo(name = "uv")
    override val uv: Double
) : UnitDetailSpecific {
}