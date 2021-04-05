package com.example.weather.screens.list_cities.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.cities.CitiesFields
import kotlinx.android.synthetic.main.recyclerview_item_list_cities.view.*

class ListCitiesAdapter: RecyclerView.Adapter<ListCitiesAdapter.ViewHolder>() {

    var listCities = mutableListOf<CitiesFields>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_list_cities, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listCities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCities[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: CitiesFields) = with(itemView) {
            cityNameListCites.text = item.cityName
        }
    }
}