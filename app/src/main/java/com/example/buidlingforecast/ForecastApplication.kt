package com.example.buidlingforecast

import android.app.Application
import android.content.Context
import com.example.buidlingforecast.data.database.WeatherDatabase
import com.example.buidlingforecast.data.network.*
import com.example.buidlingforecast.data.provider.LocationProvider
import com.example.buidlingforecast.data.provider.LocationProviderImpl
import com.example.buidlingforecast.data.provider.UnitProvider
import com.example.buidlingforecast.data.provider.UnitProviderImpl
import com.example.buidlingforecast.data.repository.ForecastRepository
import com.example.buidlingforecast.data.repository.ForecastRepositoryImpl
import com.example.buidlingforecast.ui.weather.current.WeatherViewmodelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import okhttp3.internal.connection.ConnectInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import kotlin.math.sin

class ForecastApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { WeatherDatabase(instance()) }
        bind() from singleton { instance<WeatherDatabase>().accessToWeatherDatabase() }
        bind() from singleton { instance<WeatherDatabase>().accessToLocationDatabase() }

        bind<connectivityInterceptor>() with singleton { connectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuService(instance()) }
        bind<weatherNetworkOutsource>() with singleton { weatherNetworkOutsourceImpl(instance()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl() }
        bind<ForecastRepository>() with singleton {
            ForecastRepositoryImpl(
                instance(),
                instance(),
                instance(),
                instance()
            )
        }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { WeatherViewmodelFactory(instance(), instance()) }


    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}