package com.example.weather.screens.fragments.cities.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.response.cities.CitiesFields
import com.example.weather.screens.fragments.cities.ui.viewholders.StoredCitiesViewHolder

class StoredCitiesAdapter(
    private val onItemClick: (CitiesFields) -> Unit
) : RecyclerView.Adapter<StoredCitiesViewHolder>() {

    var listCities = listOf<CitiesFields>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoredCitiesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_stored_cities, parent, false)
        return StoredCitiesViewHolder(view)
    }

    override fun getItemCount(): Int = listCities.size

    override fun onBindViewHolder(holder: StoredCitiesViewHolder, position: Int) {
        holder.bind(listCities[position])
        holder.itemView.setOnClickListener { onItemClick(listCities[position]) }
    }

}