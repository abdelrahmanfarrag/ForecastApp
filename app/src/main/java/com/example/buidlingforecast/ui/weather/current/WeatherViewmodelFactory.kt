package com.example.buidlingforecast.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.buidlingforecast.data.provider.UnitProvider
import com.example.buidlingforecast.data.repository.ForecastRepository

class WeatherViewmodelFactory(private val repository: ForecastRepository
,private val provider:UnitProvider):ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(repository,provider) as T
    }

}