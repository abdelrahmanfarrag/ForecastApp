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
import com.example.buidlingforecast.ui.weather.future.WeatherFutureViewmodelFactory
import com.example.buidlingforecast.ui.weather.future.detail.DetailFutureViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*
import org.threeten.bp.LocalDate

class ForecastApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))
        bind() from singleton { WeatherDatabase(instance()) }
        bind() from singleton { instance<WeatherDatabase>().accessToWeatherDatabase() }
        bind() from singleton { instance<WeatherDatabase>().accessToLocationDatabase() }
        bind() from singleton { instance<WeatherDatabase>().accessToFutureDatabase() }

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind<LoggingInterceptor>() with singleton { LoggingInterceptorImpl() }
        bind() from singleton { ApixuService(instance<ConnectivityInterceptor>(),instance<LoggingInterceptor>()) }
        bind<WeatherNetworkOutsource>() with singleton { WeatherNetworkOutsourceImpl(instance()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl() }
        bind<ForecastRepository>() with singleton {
            ForecastRepositoryImpl(
                instance(),
                instance(),
                instance(),
                instance(),
                instance()
            )
        }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance<Context>()) }
        bind() from provider { WeatherViewmodelFactory(instance<ForecastRepository>(), instance<UnitProvider>()) }
        bind() from provider { WeatherFutureViewmodelFactory(instance(), instance()) }
        bind() from factory { date: LocalDate ->
            DetailFutureViewModelFactory(
                date,
                instance<UnitProvider>(),
                instance<ForecastRepository>()
            )
        }
    }
    //  bind() from provider { WeatherFutureViewmodelFactory(instance(), instance()) }



override fun onCreate() {
    super.onCreate()
    AndroidThreeTen.init(this)
}
}