package com.example.buidlingforecast.ui.weather.future.detail

import androidx.lifecycle.ViewModel;
import com.example.buidlingforecast.data.provider.UnitProvider
import com.example.buidlingforecast.data.repository.ForecastRepository
import com.example.buidlingforecast.internal.lazyDeferred
import com.example.buidlingforecast.ui.base.WeatherBaseViewModel
import org.threeten.bp.LocalDate

class FutureDetailViewModel(date : LocalDate,unitProvider: UnitProvider, forecastRepository: ForecastRepository) :
    WeatherBaseViewModel(forecastRepository, unitProvider) {

    val detailWeather by lazyDeferred {
        forecastRepository.getDetailedFutureWeather(isMetric,date)
    }
}
