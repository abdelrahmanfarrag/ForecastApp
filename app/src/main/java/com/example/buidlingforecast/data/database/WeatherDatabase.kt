package com.example.buidlingforecast.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.buidlingforecast.data.database.entity.CurrentWeatherEntity
import com.example.buidlingforecast.data.database.entity.FutureWeatherEntry
import com.example.buidlingforecast.data.database.entity.Location

@Database(entities = [CurrentWeatherEntity::class, Location::class, FutureWeatherEntry::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: WeatherDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, "forecase.db").build()

    }


    abstract fun accessToWeatherDatabase(): currentWeatherDao
    abstract fun accessToLocationDatabase(): LocationDao
    abstract fun accessToFutureDatabase(): FutureDao
}