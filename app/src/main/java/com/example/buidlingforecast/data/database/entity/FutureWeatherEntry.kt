package com.example.buidlingforecast.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "future_weather", indices = [Index(value = ["date"], unique = true)])
data class FutureWeatherEntry(
    @SerializedName("date")
    val date: String,
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    @SerializedName("day")
    @Embedded
    val day: Day
){}
