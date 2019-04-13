package com.example.buidlingforecast.data.provider

import com.example.buidlingforecast.data.database.entity.Location

class LocationProviderImpl : LocationProvider {
    override suspend fun hasLocationChanged(location: Location): Boolean {
        return true
    }

    override suspend fun getLocationString(): String {
        return "Zagazig"
    }
}