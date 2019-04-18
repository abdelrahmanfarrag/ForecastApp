package com.example.buidlingforecast.data.database.unitlocalized.future.detail

import androidx.room.ColumnInfo
import org.threeten.bp.LocalDate

class DetailMetric(
    @ColumnInfo(name = "date")
    override val localDate: LocalDate,
    @ColumnInfo(name = "maxtempC")
    override val maxTemperature: Double,
    @ColumnInfo(name = "mintempC")
    override val minTemperature: Double,
    @ColumnInfo(name = "avgtempC")
    override val avgTemperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIcon: String,
    @ColumnInfo(name = "maxwindKph")
    override val maxWindSpeed: Double,
    @ColumnInfo(name = "totalprecipMm")
    override val totalPercipatition: Double,
    @ColumnInfo(name = "avgvisKm")
    override val avgVisibility: Double,
    @ColumnInfo(name = "uv")
    override val uv: Double
) : UnitDetailSpecific {
}