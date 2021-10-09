package com.example.weather.screens.fragments.cities.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.response.cities.CitiesRecord

abstract class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    open fun bind(item: CitiesRecord) {}

}