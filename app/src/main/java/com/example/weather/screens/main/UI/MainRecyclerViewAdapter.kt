package com.example.weather.screens.main.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.weather.Today.TodayWeatherMain
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class MainRecyclerViewAdapter: RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    var listWeatherMain = listOf<TodayWeatherMain>()
         set(value) {
            field = value
            notifyDataSetChanged()
          }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listWeatherMain.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listWeatherMain[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: TodayWeatherMain) = with(itemView) {
            textViewTemp.text = item.temperature.toString()
        }
    }
}