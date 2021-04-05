package com.example.weather.screens.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.App
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.data.cities.CitiesFields
import com.example.weather.screens.cities.CitiesActivity
import com.example.weather.screens.list.di.DaggerListCitiesComponent
import com.example.weather.screens.list.di.ListCitiesModule
import com.example.weather.screens.list.ui.ListCitiesAdapter
import com.example.weather.screens.main.MainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_list_cities.*
import javax.inject.Inject

class ListCitiesActivity: BaseActivity() {

    override val layout: Int
        get() = R.layout.activity_list_cities

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapterListCities: ListCitiesAdapter

    private val component by lazy {
        DaggerListCitiesComponent.builder()
            .appComponent((application as App).appComponent)
            .listCitiesModule(ListCitiesModule(this))
            .build()
    }

    @Inject
    lateinit var viewModel: ListCitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewListCities.layoutManager = linearLayoutManager

        component.inject(this)

        adapterListCities =
            ListCitiesAdapter()
        recyclerViewListCities.adapter = adapterListCities

        viewModel.listCitiesLiveData.observe(this, Observer {
            adapterListCities.listCities = it
        })

    }

}