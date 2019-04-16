package com.example.buidlingforecast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.buidlingforecast.data.network.response.CurrentWeather
import com.example.buidlingforecast.data.network.response.FutureResponse
import com.example.buidlingforecast.internal.NoConnectivityException

class weatherNetworkOutsourceImpl(private val apixuService: ApixuService) : weatherNetworkOutsource {


    private val _updatedCurrentLiveData = MutableLiveData<CurrentWeather>()
    private val _updateFutureLiveData = MutableLiveData<FutureResponse>()

    override val downloadedWearherData: LiveData<CurrentWeather>
        get() = _updatedCurrentLiveData

    override val downloadedFutureWeatherData: LiveData<FutureResponse>
        get() = _updateFutureLiveData


    override suspend fun fetchCurrentWeather(location: String, lang: String) {
        try {
            val fetchedData = apixuService.getCurrentWeather(location, lang).await()
            _updatedCurrentLiveData.postValue(fetchedData)
        } catch (e: NoConnectivityException) {

            Log.e("connectivity", "No connectivity Network $e")

        }
    }


    override suspend fun fetchFutureWeather(location: String, lang: String, days: Int) {
        try {
            val fetchFutureData = apixuService.getFutureWeather(location, days, lang).await()
            _updateFutureLiveData.postValue(fetchFutureData)

        } catch (e: NoConnectivityException) {
            Log.e("connectivity", "No connectivity Network $e")

        }
    }
}