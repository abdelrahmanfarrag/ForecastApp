package com.example.buidlingforecast.ui.weather.future

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.buidlingforecast.R
import com.example.buidlingforecast.data.database.LocalDateConverter
import com.example.buidlingforecast.data.database.unitlocalized.future.list.UnitSpecificFutureWeatherEntry
import com.example.buidlingforecast.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.future_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.threeten.bp.LocalDate

class FutureWeatherFragment : ScopedFragment(), KodeinAware {

    private val factory: WeatherFutureViewmodelFactory by instance()
    override val kodein: Kodein by closestKodein()


    private lateinit var viewModel: FutureWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(FutureWeatherViewModel::class.java)
        bindUI()

    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val entries = viewModel.fetchWeatherFromRepo.await()
        val location = viewModel.fetchUserLocation.await()

        location.observe(this@FutureWeatherFragment, Observer {
            if (location == null) return@Observer
            (activity as AppCompatActivity)?.supportActionBar?.subtitle = "cairo"
        })

        entries.observe(this@FutureWeatherFragment, Observer { weatherEntries ->

            if (weatherEntries == null) return@Observer
            group_loading.visibility = View.GONE
            (activity as AppCompatActivity)?.supportActionBar?.subtitle = "next week"
            initRecycler(weatherEntries.toFutureItems())
        })
    }

    private fun List<UnitSpecificFutureWeatherEntry>.toFutureItems(): List<FutureWeatherItem> {
        return this.map {
            FutureWeatherItem(it)
        }
    }


    private fun initRecycler(entries: List<FutureWeatherItem>) {
        val groupie_adapter = GroupAdapter<ViewHolder>().apply {
            addAll(entries)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FutureWeatherFragment.context)
            adapter = groupie_adapter
        }
        groupie_adapter.setOnItemClickListener { item, view ->
            // Toast.makeText(this.context, "Show future details", Toast.LENGTH_LONG).show()
            (item as FutureWeatherItem)?.let { futureWeatherItem ->
                moveToDetailScreen(futureWeatherItem.entry.localDate, view)

            }
        }
    }

    private fun moveToDetailScreen(date: LocalDate, view: View) {
        val dateString = LocalDateConverter.dateToString(date)!!
        val action = FutureWeatherFragmentDirections.actionDetail(dateString)
        Navigation.findNavController(view).navigate(action)
    }


}
