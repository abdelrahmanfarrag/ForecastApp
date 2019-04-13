package com.example.buidlingforecast.data.provider

import com.example.buidlingforecast.data.database.entity.Location

interface LocationProvider {

   suspend fun hasLocationChanged(location: Location): Boolean

    suspend fun getLocationString(): String

}