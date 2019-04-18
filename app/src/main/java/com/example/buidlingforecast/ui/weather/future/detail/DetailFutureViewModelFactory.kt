package com.example.buidlingforecast.ui.weather.future.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.buidlingforecast.data.provider.UnitProvider
import com.example.buidlingforecast.data.repository.ForecastRepository
import org.threeten.bp.LocalDate

class DetailFutureViewModelFactory(
    private val localDate: LocalDate,
    private val unitProvider: UnitProvider,
    private val repository: ForecastRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureDetailViewModel(localDate, unitProvider, repository) as T
    }
}