package com.example.buidlingforecast.data.repository

import androidx.lifecycle.LiveData
import com.example.buidlingforecast.data.database.FutureDao
import com.example.buidlingforecast.data.database.LocationDao
import com.example.buidlingforecast.data.database.currentWeatherDao
import com.example.buidlingforecast.data.database.entity.Location
import com.example.buidlingforecast.data.database.unitlocalized.current.unitSpecificCurrentWeatherEntry
import com.example.buidlingforecast.data.database.unitlocalized.future.UnitSpecificFutureWeatherEntry
import com.example.buidlingforecast.data.network.response.CurrentWeather
import com.example.buidlingforecast.data.network.response.FutureResponse
import com.example.buidlingforecast.data.network.weatherNetworkOutsource
import com.example.buidlingforecast.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import java.util.*


const val FORECAST_DAYS_COUNT: Int = 7

class ForecastRepositoryImpl(
    private val weatherDao: currentWeatherDao,
    private val locationDao: LocationDao,
    private val fututreDao: FutureDao,
    private val weatherNetworkSource: weatherNetworkOutsource,
    private val locationProvider: LocationProvider
) : ForecastRepository {

    override suspend fun getWeatherLocation(): LiveData<Location> {
        return withContext(Dispatchers.IO) {
            return@withContext locationDao.getLocation()
        }
    }

    init {

        weatherNetworkSource.apply {
            downloadedWearherData.observeForever { currentWeather ->
                persistData(currentWeather)
            }
            downloadedFutureWeatherData.observeForever { futureWeather ->
                persistFutureData(futureWeather)
            }
        }
    }

    override suspend fun getCurrentWeather(isMetric: Boolean): LiveData<out unitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            instantiateNetworkCall()
            return@withContext if (isMetric) weatherDao.getWeatherMetric()
            else weatherDao.getWearherImperial()
        }
    }

    override suspend fun getFutureWeather(
        startDate: LocalDate,
        isMetric: Boolean
    ): LiveData<out List<UnitSpecificFutureWeatherEntry>> {
        return withContext(Dispatchers.IO) {
            instantiateNetworkCall()
            return@withContext if (isMetric) fututreDao.getMetricFutureWeather(startDate)
            else fututreDao.getImperialFutureWeather(startDate)
        }
    }


    private fun persistData(fetchWeather: CurrentWeather) {
        GlobalScope.launch(Dispatchers.IO) {
            weatherDao.upsertWeatherEntry(fetchWeather.current)
            locationDao.upsertLocation(fetchWeather.location)
        }
    }

    private fun deleteOldEntries() {
        val today = LocalDate.now()
        fututreDao.deleteOldEntries(today)
    }

    private fun persistFutureData(futureWeather: FutureResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            deleteOldEntries()
            val futureList = futureWeather.forecastEntries.entries
            fututreDao.upsertFutureWeather(futureList)
            locationDao.upsertLocation(futureWeather.location)
        }

    }

    private suspend fun instantiateNetworkCall() {
        val lastKnownLocation = locationDao.getLocation().value
        if (lastKnownLocation == null || locationProvider.hasLocationChanged(lastKnownLocation)) {
            fetchDataFromNetwork()
            fetchFutureWeatherFromNetwork()

            return
        }
        if (lastTimeCalled(lastKnownLocation.zoneDateTime))
            fetchDataFromNetwork()

        if (isFutureWeatherNeeded()) {
            fetchFutureWeatherFromNetwork()
        }
    }

    private fun lastTimeCalled(lastTimeCalled: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastTimeCalled.isBefore(thirtyMinutesAgo)

    }

    private suspend fun isFutureWeatherNeeded(): Boolean {
        val today = LocalDate.now()
        val futureWeatherCount = fututreDao.countFutureEntries(today)
        return futureWeatherCount < FORECAST_DAYS_COUNT
    }

    private suspend fun fetchDataFromNetwork() {
        weatherNetworkSource.fetchCurrentWeather(locationProvider.getLocationString(), Locale.getDefault().language)
    }

    private suspend fun fetchFutureWeatherFromNetwork() {
        weatherNetworkSource.fetchFutureWeather(locationProvider.getLocationString(), Locale.getDefault().language, 1)

    }

}