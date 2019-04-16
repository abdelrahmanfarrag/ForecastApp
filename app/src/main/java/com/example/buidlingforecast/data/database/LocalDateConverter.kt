package com.example.buidlingforecast.data.database

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

object LocalDateConverter {
    @TypeConverter
    @JvmStatic
    fun stringToDate(string: String?) = string?.let { converyStr ->
        LocalDate.parse(converyStr, DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @TypeConverter
    @JvmStatic
    fun dateToString(localDate: LocalDate?) = localDate?.format(DateTimeFormatter.ISO_LOCAL_DATE)

}