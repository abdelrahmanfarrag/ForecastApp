package com.example.buidlingforecast.ui.base

import androidx.lifecycle.ViewModel
import com.example.buidlingforecast.data.provider.UnitProvider
import com.example.buidlingforecast.data.repository.ForecastRepository
import com.example.buidlingforecast.internal.UnitSystem
import com.example.buidlingforecast.internal.lazyDeferred

abstract class WeatherBaseViewModel(
    private val repository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val fetchUserLocation by lazyDeferred {
        repository.getWeatherLocation()
    }

}