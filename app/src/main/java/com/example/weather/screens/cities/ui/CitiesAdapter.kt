package com.example.weather.screens.cities.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.cities.Cities
import com.example.weather.data.cities.CitiesRecord
import kotlinx.android.synthetic.main.recyclerview_item_cities.view.*

class CitiesAdapter: RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    var listCities = listOf<CitiesRecord>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_cities, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listCities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCities[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: CitiesRecord) = with(itemView) {
            cityNameItem.text = item.cityFields?.cityName.toString()
            countyNameItem.text = item.cityFields?.countryCity.toString()
        }
    }

}