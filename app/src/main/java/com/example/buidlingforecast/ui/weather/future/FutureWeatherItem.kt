package com.example.buidlingforecast.ui.weather.future

import com.example.buidlingforecast.R
import com.example.buidlingforecast.data.database.unitlocalized.future.FutureMetric
import com.example.buidlingforecast.data.database.unitlocalized.future.UnitSpecificFutureWeatherEntry
import com.example.buidlingforecast.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_future_weather.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle


class FutureWeatherItem(val entry : UnitSpecificFutureWeatherEntry): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            condition.text = entry.conditionText
            updateDate()
            updateTemperature()
            settingWeatherImage()



        }
    }

    override fun getLayout(): Int = R.layout.item_future_weather

    private fun ViewHolder.updateDate(){
        val dtFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        date.text = entry.localDate.format(dtFormatter)
    }
    private fun ViewHolder.updateTemperature(){
        val unit = if (entry is FutureMetric) "°C" else "°F"
        temperature.text ="${entry.avgTemperature}$unit"
    }
    private fun ViewHolder.settingWeatherImage() {
        GlideApp.with(this.containerView)
            .load("https://" + entry.conditionIcon)
            .into(image_conditions)

    }

}