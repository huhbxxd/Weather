package com.example.weather.screens.cities.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.cities.Cities
import com.example.weather.data.cities.CitiesFields
import com.example.weather.data.cities.CitiesRecord
import com.example.weather.screens.cities.ui.viewholders.BaseViewHolder
import com.example.weather.screens.cities.ui.viewholders.CitiesViewHolder
import com.example.weather.screens.cities.ui.viewholders.LoadingViewHolder
import kotlinx.android.synthetic.main.recyclerview_item_cities.view.*
import java.lang.IllegalArgumentException

class CitiesAdapter(
    private val onItemClick: (city: CitiesFields) -> Unit
): RecyclerView.Adapter<BaseViewHolder>() {

    private companion object {
        const val TYPE_CONTENT = 0
        const val TYPE_LOADING = 1
    }

    var listCities = listOf<CitiesRecord>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var hasLoading = true

    override fun getItemViewType(position: Int): Int {
        return if (isLoadingPosition(position)) TYPE_LOADING else TYPE_CONTENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TYPE_CONTENT -> CitiesViewHolder(inflater.inflate(R.layout.recyclerview_item_cities, parent, false))
            TYPE_LOADING -> LoadingViewHolder(inflater.inflate(R.layout.item_loading, parent, false))
            else -> throw(IllegalArgumentException())
        }
    }

    override fun getItemCount(): Int = if(listCities.isEmpty()) 0 else getSize()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if(!isLoadingPosition(position)) {
            holder.bind(listCities[position])
            holder.itemView.setOnClickListener { onItemClick(listCities[position].cityFields!!) }
        }
    }

    private fun getSize() = if(hasLoading) listCities.size + 1 else listCities.size

    private fun isLoadingPosition(position: Int) = position == listCities.size

}