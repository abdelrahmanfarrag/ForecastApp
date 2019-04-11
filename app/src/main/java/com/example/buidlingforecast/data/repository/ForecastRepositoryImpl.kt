package com.example.buidlingforecast.data.repository

import androidx.lifecycle.LiveData
import com.example.buidlingforecast.data.database.currentWeatherDao
import com.example.buidlingforecast.data.database.entity.CurrentWeatherEntity
import com.example.buidlingforecast.data.database.unitlocalized.unitSpecificCurrentWeatherEntry
import com.example.buidlingforecast.data.network.response.CurrentWeather
import com.example.buidlingforecast.data.network.weatherNetworkOutsource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val weatherDao: currentWeatherDao,
    private val weatherNetworkSource: weatherNetworkOutsource
) : ForecastRepository {

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
        }
    }

    private suspend fun instantiateNetworkCall() {
        if (lastTimeCalled(ZonedDateTime.now().minusHours(1)))
            fetchDataFromNetwork()
    }

    private fun lastTimeCalled(lastTimeCalled: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastTimeCalled.isBefore(thirtyMinutesAgo)

    }

    private suspend fun fetchDataFromNetwork() {
        weatherNetworkSource.fetchCurrentWeather("london", Locale.getDefault().language)
    }

}