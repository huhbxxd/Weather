package com.example.weather.screens.cities.ui.viewholders

import android.view.View
import com.example.weather.data.cities.CitiesRecord
import kotlinx.android.synthetic.main.recyclerview_item_search_cities.view.*

class CitiesViewHolder(itemView: View): BaseViewHolder(itemView) {

    override fun bind(item: CitiesRecord) = with(itemView) {
        cityNameItem.text = item.cityFields?.cityName.toString()
        countyNameItem.text = item.cityFields?.timeZone?.replace("_", " ")
    }

}