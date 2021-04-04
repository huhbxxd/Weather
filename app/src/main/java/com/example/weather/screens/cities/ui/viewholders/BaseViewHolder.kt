package com.example.weather.screens.cities.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.cities.CitiesRecord

abstract class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    open fun bind(item: CitiesRecord) {}

}