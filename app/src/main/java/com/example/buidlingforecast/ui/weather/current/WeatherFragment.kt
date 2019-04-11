package com.example.buidlingforecast.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.buidlingforecast.R
import com.example.buidlingforecast.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class WeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val viewmodelFactory by instance<WeatherViewmodelFactory>()


    companion object {
        fun newInstance() = WeatherFragment()
    }

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
            textView.text = it.toString()
        })

    }


}
