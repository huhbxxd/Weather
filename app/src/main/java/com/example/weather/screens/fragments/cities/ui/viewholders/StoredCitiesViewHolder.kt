package com.example.weather.screens.fragments.cities.ui.viewholders

import android.view.View
import com.example.weather.data.response.cities.CitiesFields
import kotlinx.android.synthetic.main.recyclerview_item_stored_cities.view.*

class StoredCitiesViewHolder(itemView: View): BaseViewHolder(itemView) {

    fun bind(item: CitiesFields) = with(itemView) {
        cityNameListCites.text = item.cityName
        cityTimeZone.text = item.timeZone?.replace("_", " ")
    }

}