package com.example.weather.screens.fragments.cities.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.response.cities.CitiesFields
import com.example.weather.data.response.cities.CitiesRecord
import com.example.weather.screens.fragments.cities.ui.viewholders.BaseViewHolder
import com.example.weather.screens.fragments.cities.ui.viewholders.CitiesViewHolder
import com.example.weather.screens.fragments.cities.ui.viewholders.LoadingViewHolder
import java.lang.IllegalArgumentException

class SearchCitiesAdapter(
    private val onItemClick: (CitiesFields) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {

    private companion object {
        const val TYPE_CONTENT = 0
        const val TYPE_LOADING = 1
    }

    private var listCities = mutableListOf<CitiesRecord>()

    var hasLoading = true

    override fun getItemViewType(position: Int): Int {
        return if (isLoadingPosition(position)) TYPE_LOADING else TYPE_CONTENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_CONTENT -> CitiesViewHolder(
                inflater.inflate(
                    R.layout.recyclerview_item_search_cities,
                    parent,
                    false
                )
            )
            TYPE_LOADING -> LoadingViewHolder(
                inflater.inflate(
                    R.layout.item_loading,
                    parent,
                    false
                )
            )
            else -> throw(IllegalArgumentException())
        }
    }

    override fun getItemCount(): Int = if (listCities.isEmpty()) 0 else getSize()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (!isLoadingPosition(position)) {
            holder.bind(listCities[position])
            holder.itemView.setOnClickListener { onItemClick(listCities[position].cityFields!!) }
        }
    }

    private fun getSize() = if (hasLoading) listCities.size + 1 else listCities.size

    private fun isLoadingPosition(position: Int) = position == listCities.size

    fun setCities(list: List<CitiesRecord>) {
        listCities.addAll(list)
        notifyItemRangeChanged(listCities.size, list.size)
    }

    fun citiesClear() = listCities.clear()

}