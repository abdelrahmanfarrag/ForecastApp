package com.example.buidlingforecast.ui.weather.future

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.buidlingforecast.data.provider.UnitProvider
import com.example.buidlingforecast.data.repository.ForecastRepository

@Suppress("UNCHECKED_CAST")
class WeatherFutureViewmodelFactory(
    private val repository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureWeatherViewModel(repository, unitProvider) as T
    }

}