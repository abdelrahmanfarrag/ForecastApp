package com.example.buidlingforecast.data.provider

import com.example.buidlingforecast.internal.UnitSystem

interface UnitProvider {
    fun  getUnitSystem():UnitSystem
}