package com.example.weather.screens.fragments.cities.ui.viewholders

import android.view.View
import com.example.weather.data.response.cities.CitiesRecord

class CitiesViewHolder(itemView: View): BaseViewHolder(itemView) {

    override fun bind(item: CitiesRecord) = with(itemView) {
//        cityNameItem.text = item.cityFields?.cityName.toString()
//        countyNameItem.text = item.cityFields?.timeZone?.replace("_", " ")
    }

}