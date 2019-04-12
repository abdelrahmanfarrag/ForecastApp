package com.example.buidlingforecast.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.buidlingforecast.R
import com.example.buidlingforecast.internal.glide.GlideApp
import com.example.buidlingforecast.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_detail_fragment.*
import kotlinx.android.synthetic.main.weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class WeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val viewmodelFactory by instance<WeatherViewmodelFactory>()

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewmodelFactory).get(WeatherViewModel::class.java)
        bindUi()
    }

    private fun bindUi() = launch {
        val currentWeather = viewModel.fetchWeatherFromRepo.await()
        currentWeather.observe(this@WeatherFragment, Observer {

            if (it == null) return@Observer
            val loadingUrl = "https:${it.conditionImgUrl}"
            group_loading.visibility = View.GONE
            updateLocation("Los Angelos")
            updateDate("")
            updateTemperature(it.tempreature, it.feelsLikeTemperature)
            updateCondition(it.conditionText)
            updatePerciptation(it.precipationVolume)
            updateWind(it.windDirection, it.windSpeed)
            updateSpeed(it.windSpeed)
            settingWeatherImage(loadingUrl)
        })

    }
    private fun settingWeatherImage(url:String){
        GlideApp.with(this@WeatherFragment)
            .load(url)
            .into(imageView_condition_icon)

    }

    private fun chooseLocationUnit(metric: String, imperial: String): String {
        return if (viewModel.isMetric) metric else imperial

    }

    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateTemperature(temp: Double, feelsLike: Double) {
        val unit = chooseLocationUnit("°C", "°F")
        textView_temperature.text = "$temp$unit"
        textView_feels_like_temperature.text = "$feelsLike$unit"

    }

    private fun updateCondition(condition: String) {

        textView_condition.text = condition
    }

    private fun updatePerciptation(perceptationVolume: Double) {
        val percipationUnit = chooseLocationUnit("mm", "in")
        textView_precipitation.text = "Percipation : $perceptationVolume$percipationUnit"


    }

    private fun updateWind(windDirection: String, windSpeed: Double) {
        val windUnit = chooseLocationUnit("kph", "mph")
        textView_wind.text = "Wind : $windDirection,$windSpeed$windUnit"
    }

    private fun updateSpeed(visiblityDisance: Double) {
        val visiblityUnit = chooseLocationUnit("km", "mi.")
        textView_visibility.text = "Visiblity : $visiblityDisance$visiblityUnit"

    }

    private fun updateDate(date: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

}
