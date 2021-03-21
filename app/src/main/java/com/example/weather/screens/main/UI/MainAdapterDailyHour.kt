package com.example.weather.screens.main.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.weather.daily_day.DailyWeather

class MainAdapterDailyHour: RecyclerView.Adapter<MainAdapterDailyHour.ViewHolder>() {

//    var weatherDailyHour

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_hourly, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = weatherDailyHour.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weatherDaulyHour[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: DailyWeather) = with(itemView) {
        }
    }
}