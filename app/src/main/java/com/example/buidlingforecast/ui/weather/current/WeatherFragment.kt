package com.example.buidlingforecast.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.buidlingforecast.R
import com.example.buidlingforecast.data.ApixuService
import kotlinx.android.synthetic.main.weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherFragment : Fragment() {

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
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        // TODO: Use the ViewModel
        val apiService = ApixuService.invoke()
        GlobalScope.launch(Dispatchers.Main) {
            val currentWeather = apiService.getCurrentWeather("london","en").await()
            textView.text = currentWeather.toString()

        }
    }

}
