package com.example.weather.screens.cities.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.cities.CitiesFields
import com.example.weather.screens.cities.ui.viewholders.BaseViewHolder
import com.example.weather.screens.cities.ui.viewholders.ListCitiesViewHolder
import kotlinx.android.synthetic.main.recyclerview_item_list_cities.view.*

class ListCitiesAdapter: RecyclerView.Adapter<ListCitiesViewHolder>() {

    var listCities = listOf<CitiesFields>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCitiesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_list_cities, parent, false)
        return ListCitiesViewHolder(view)
    }

    override fun getItemCount(): Int = listCities.size

    override fun onBindViewHolder(holder: ListCitiesViewHolder, position: Int) {
        holder.bind(listCities[position])
    }

}