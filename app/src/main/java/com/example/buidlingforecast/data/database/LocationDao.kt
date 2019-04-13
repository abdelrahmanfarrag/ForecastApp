package com.example.buidlingforecast.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.buidlingforecast.data.database.entity.Location
import com.example.buidlingforecast.data.database.entity.WEATHER_PRIMARY_KEY

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertLocation(location: Location)

    @Query("select * from weather_location where id = $WEATHER_PRIMARY_KEY")
    fun getLocation(): LiveData<Location>
}