package com.example.buidlingforecast.data.repository

import androidx.lifecycle.LiveData
import com.example.buidlingforecast.data.database.LocationDao
import com.example.buidlingforecast.data.database.currentWeatherDao
import com.example.buidlingforecast.data.database.entity.CurrentWeatherEntity
import com.example.buidlingforecast.data.database.entity.Location
import com.example.buidlingforecast.data.database.unitlocalized.unitSpecificCurrentWeatherEntry
import com.example.buidlingforecast.data.network.response.CurrentWeather
import com.example.buidlingforecast.data.network.weatherNetworkOutsource
import com.example.buidlingforecast.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val weatherDao: currentWeatherDao,
    private val locationDao: LocationDao,
    private val weatherNetworkSource: weatherNetworkOutsource,
    private val locationProvider: LocationProvider
) : ForecastRepository {
    override suspend fun getWeatherLocation(): LiveData<Location> {
        return withContext(Dispatchers.IO) {
            return@withContext locationDao.getLocation()
        }
    }

    init {

        weatherNetworkSource.downloadedWearherData.observeForever { currentWeather ->
            persistData(currentWeather)
        }
    }

    override suspend fun getCurrentWeather(isMetric: Boolean): LiveData<out unitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            instantiateNetworkCall()
            return@withContext if (isMetric) weatherDao.getWeatherMetric()
            else weatherDao.getWearherImperial()
        }
    }

    private fun persistData(fetchWeather: CurrentWeather) {
        GlobalScope.launch(Dispatchers.IO) {
            weatherDao.upsertWeatherEntry(fetchWeather.current)
            locationDao.upsertLocation(fetchWeather.location)
        }
    }

    private suspend fun instantiateNetworkCall() {
        val lastKnownLocation = locationDao.getLocation().value
        if (lastKnownLocation == null || locationProvider.hasLocationChanged(lastKnownLocation)) {
            fetchDataFromNetwork()
            return
        }
        if (lastTimeCalled(lastKnownLocation.zoneDateTime))
            fetchDataFromNetwork()
    }

    private fun lastTimeCalled(lastTimeCalled: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastTimeCalled.isBefore(thirtyMinutesAgo)

    }

    private suspend fun fetchDataFromNetwork() {
        weatherNetworkSource.fetchCurrentWeather(locationProvider.getLocationString(), Locale.getDefault().language)
    }

}