package com.example.weather.screens.fragments.cities.ui.viewholders

import android.view.View
import com.example.weather.data.response.cities.CitiesFields

class StoredCitiesViewHolder(itemView: View): BaseViewHolder(itemView) {

    fun bind(item: CitiesFields) = with(itemView) {
//        cityNameListCites.text = item.cityName
//        cityTimeZone.text = item.timeZone?.replace("_", " ")
    }

}