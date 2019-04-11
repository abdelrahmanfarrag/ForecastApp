package com.example.buidlingforecast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.buidlingforecast.data.network.response.CurrentWeather
import com.example.buidlingforecast.internal.NoConnectivityException

class weatherNetworkOutsourceImpl(private val apixuService: ApixuService) : weatherNetworkOutsource {

    private val _updatedLiveData = MutableLiveData<CurrentWeather>()

    override val downloadedWearherData: LiveData<CurrentWeather>
        get() = _updatedLiveData

    override suspend fun fetchCurrentWeather(location: String, lang: String) {
        try {
            val fetchedData = apixuService.getCurrentWeather(location, lang).await()
            _updatedLiveData.postValue(fetchedData)
        } catch (e: NoConnectivityException) {

            Log.e("connectivity", "No connectivity Network $e")

        }
    }
}