package com.example.weather.screens.main.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.data.weather.daily.hour.HourlyWeather
import kotlinx.android.synthetic.main.recyclerview_item_hourly.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class MainAdapterDailyHour(
    private val testFunItem: (hour: HourlyWeather) -> Unit
): RecyclerView.Adapter<MainAdapterDailyHour.ViewHolder>() {

    private companion object {
        const val ICON_URL = "http://openweathermap.org/img/wn/"
    }

    var listDailyHour = listOf<HourlyWeather>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_hourly, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listDailyHour.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDailyHour[position])
        holder.itemView.setOnClickListener { testFunItem(listDailyHour[position]) }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: HourlyWeather) = with(itemView) {
            dailyHour.text = dateFormatter(item.dt)
            Glide.with(context).load(ICON_URL + item.weather[0].icon + ".png").into(imageHourlyIcon)
            dailyHourTemp.text = item.temperature.roundToInt().toString()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun dateFormatter(unix: Int): String {
        return SimpleDateFormat("k") // "k" it's number of hours from table of SimpleDateFormat
            .format(Date(unix.toLong() * 1000))
    }

}