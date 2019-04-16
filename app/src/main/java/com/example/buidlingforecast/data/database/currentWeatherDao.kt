package com.example.buidlingforecast.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.buidlingforecast.data.database.entity.CurrentWeatherEntity
import com.example.buidlingforecast.data.database.entity.PRIMARY_KEY
import com.example.buidlingforecast.data.database.unitlocalized.current.Imperial
import com.example.buidlingforecast.data.database.unitlocalized.current.Metric

@Dao
interface currentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertWeatherEntry(entity: CurrentWeatherEntity)

 @Query("SELECT * From CURRENT_WEATHER where id = $PRIMARY_KEY")
    fun getWeatherMetric():LiveData<Metric>

    @Query("SELECT * From CURRENT_WEATHER where id = $PRIMARY_KEY")
    fun getWearherImperial():LiveData<Imperial>

}