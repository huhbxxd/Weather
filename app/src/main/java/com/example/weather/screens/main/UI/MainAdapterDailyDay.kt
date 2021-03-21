package com.example.weather.screens.main.UI

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.data.weather.daily_day.DailyWeather
import kotlinx.android.synthetic.main.recyclerview_item_daily_day.view.*
import java.text.SimpleDateFormat
import java.util.*


class MainAdapterDailyDay(): RecyclerView.Adapter<MainAdapterDailyDay.ViewHolder>() {

    private companion object {
        val ICON_URL = "http://openweathermap.org/img/wn/"
    }

    var listWeatherDaily = listOf<DailyWeather>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_daily_day, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listWeatherDaily.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listWeatherDaily[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: DailyWeather) = with(itemView) {
            dayWeek.text = dateFormatter(item.dt!!)
            tempDayWeek.text = item.temperature?.day.toString()
            tempNightWeek.text = item.temperature?.night.toString()
            Glide.with(context).load(ICON_URL + item.weather!![0].icon!! + ".png").into(imageWeatherDaily)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun dateFormatter(unix: Int): String {
        return SimpleDateFormat("EEEE") // "EEEE" it's full name day of week from table of SimpleDateFormat
            .format(Date(unix.toLong() * 1000))
    }

}