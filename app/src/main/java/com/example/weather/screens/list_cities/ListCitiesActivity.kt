package com.example.weather.screens.list_cities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.data.cities.CitiesFields
import com.example.weather.screens.cities.CitiesActivity
import com.example.weather.screens.list_cities.ui.ListCitiesAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_list_cities.*

class ListCitiesActivity: BaseActivity() {

    override val layout: Int
        get() = R.layout.activity_list_cities

    private val citiesList = mutableListOf<CitiesFields>()

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapterListCities: ListCitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(CitiesActivity.LIST_CITIES, Context.MODE_PRIVATE)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewListCities.layoutManager = linearLayoutManager

        adapterListCities =
            ListCitiesAdapter()
        adapterListCities.listCities = getListCities()
        recyclerViewListCities.adapter = adapterListCities

    }

    fun getListCities(): MutableList<CitiesFields> {
        val type = object : TypeToken<CitiesFields>(){}.type
        val city = Gson().fromJson<CitiesFields>(sharedPreferences.getString("samara", null), type)
        citiesList.add(city)
        return citiesList
    }

}