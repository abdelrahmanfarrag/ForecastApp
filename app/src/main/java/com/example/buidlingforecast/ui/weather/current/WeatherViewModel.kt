package com.example.buidlingforecast.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.example.buidlingforecast.data.repository.ForecastRepository
import com.example.buidlingforecast.internal.lazyDeferred

class WeatherViewModel(private val repository: ForecastRepository) : ViewModel() {
    val isMetric :Boolean
    get() = true

    val fetchWeatherFromRepo by lazyDeferred{
        repository.getCurrentWeather(isMetric)
    }
}
