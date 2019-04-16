package com.example.buidlingforecast.ui.weather.future

import androidx.lifecycle.ViewModel;
import com.example.buidlingforecast.data.provider.UnitProvider
import com.example.buidlingforecast.data.repository.ForecastRepository
import com.example.buidlingforecast.internal.UnitSystem
import com.example.buidlingforecast.internal.lazyDeferred
import com.example.buidlingforecast.ui.base.WeatherBaseViewModel
import org.threeten.bp.LocalDate

class FutureWeatherViewModel(
    private val repository: ForecastRepository,
    private val unitProvider: UnitProvider
) : WeatherBaseViewModel(repository,unitProvider) {

    val fetchWeatherFromRepo by lazyDeferred {
        repository.getFutureWeather(LocalDate.now(),isMetric)
    }

}
