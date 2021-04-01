package com.example.weather.screens.cities

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.App
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.screens.cities.di.CitiesModule
import com.example.weather.screens.cities.di.DaggerCitiesComponent
import com.example.weather.screens.cities.ui.CitiesAdapter
import kotlinx.android.synthetic.main.activity_cities.*
import javax.inject.Inject

class CitiesActivity: BaseActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapterCities: CitiesAdapter

    override val layout: Int
        get() = R.layout.activity_cities

    private val component by lazy {
        DaggerCitiesComponent.builder()
            .appComponent((application as App).appComponent)
            .citiesModule(CitiesModule(this))
            .build()
    }

    @Inject
    lateinit var viewModel: CitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayoutManager = LinearLayoutManager(this)

        component.inject(this)

        adapterCities = CitiesAdapter()
        recyclerViewCities.adapter = adapterCities

        if(Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also {
                viewModel.onSearch(it)
            }
        }
    }
}