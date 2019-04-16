package com.example.buidlingforecast.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.buidlingforecast.data.database.entity.FutureWeatherEntry
import com.example.buidlingforecast.data.database.unitlocalized.future.FutureImperial
import com.example.buidlingforecast.data.database.unitlocalized.future.FutureMetric
import org.threeten.bp.LocalDate

@Dao
interface FutureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertFutureWeather(entries: List<FutureWeatherEntry>)

    @Query("select * from future_weather where date(date) >= date(:date) ")
    fun getMetricFutureWeather(date: LocalDate): LiveData<List<FutureMetric>>

    @Query("select * from future_weather where date(date) >= date(:date) ")
    fun getImperialFutureWeather(date: LocalDate): LiveData<List<FutureImperial>>

    @Query("select count(id) from future_weather where date(date) >= date(:strDate)")
    fun countFutureEntries(strDate: LocalDate):Int

    @Query("delete from future_weather where date(date) < date(:toKeep)")
    fun  deleteOldEntries(toKeep: LocalDate)

}