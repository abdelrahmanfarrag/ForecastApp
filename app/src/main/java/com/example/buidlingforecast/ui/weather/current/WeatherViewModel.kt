package com.example.buidlingforecast.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.example.buidlingforecast.data.provider.UnitProvider
import com.example.buidlingforecast.data.repository.ForecastRepository
import com.example.buidlingforecast.internal.UnitSystem
import com.example.buidlingforecast.internal.lazyDeferred

class WeatherViewModel(
    private val repository: ForecastRepository
    , provider: UnitProvider
) : ViewModel() {

    private val unitSystem = provider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val fetchWeatherFromRepo by lazyDeferred {
        repository.getCurrentWeather(isMetric)
    }
}
