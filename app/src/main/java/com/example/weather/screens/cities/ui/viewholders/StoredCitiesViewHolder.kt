package com.example.weather.screens.cities.ui.viewholders

import android.view.View
import com.example.weather.data.cities.CitiesFields
import kotlinx.android.synthetic.main.recyclerview_item_stored_cities.view.*

class StoredCitiesViewHolder(itemView: View): BaseViewHolder(itemView) {

    fun bind(item: CitiesFields) = with(itemView) {
        cityNameListCites.text = item.cityName
        cityTimeZone.text = item.timeZone?.replace("_", " ")
    }

}