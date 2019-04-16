package com.example.buidlingforecast.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.example.buidlingforecast.data.provider.UnitProvider
import com.example.buidlingforecast.data.repository.ForecastRepository
import com.example.buidlingforecast.internal.UnitSystem
import com.example.buidlingforecast.internal.lazyDeferred
import com.example.buidlingforecast.ui.base.WeatherBaseViewModel

class WeatherViewModel(
    private val repository: ForecastRepository
    , provider: UnitProvider
) : WeatherBaseViewModel(repository, provider) {


    val fetchWeatherFromRepo by lazyDeferred {
        repository.getCurrentWeather(isMetric)
    }

}
