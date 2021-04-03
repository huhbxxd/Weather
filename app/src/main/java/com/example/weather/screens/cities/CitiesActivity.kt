package com.example.weather.screens.cities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.App
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.data.cities.CitiesRecord
import com.example.weather.screens.cities.di.CitiesModule
import com.example.weather.screens.cities.di.DaggerCitiesComponent
import com.example.weather.screens.cities.ui.CitiesAdapter
import com.example.weather.screens.main.MainActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_cities.*
import javax.inject.Inject

class CitiesActivity: BaseActivity(){

    override val layout: Int
        get() = R.layout.activity_cities

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapterCities: CitiesAdapter

    private val component by lazy {
        DaggerCitiesComponent.builder()
            .appComponent((application as App).appComponent)
            .citiesModule(CitiesModule(this))
            .build()
    }

    @Inject
    lateinit var viewModel: CitiesViewModel

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         val inflater = menuInflater
            .apply {
                inflate(R.menu.search_city_menu, menu)
            }
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = (menu?.findItem(R.id.actionSearch)?.actionView as SearchView)
            .apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
            }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onSearch(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearch(newText!!)
                return true
            }

        })
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewCities.layoutManager = linearLayoutManager

        component.inject(this)

        adapterCities = CitiesAdapter(::onItemClick)
        recyclerViewCities.adapter = adapterCities

    }

    override fun onResume() {
        super.onResume()

        viewModel.citiesViewModel.observe(this, Observer {
            adapterCities.listCities = it.citiesRecords!!
        })

    }

    private fun onItemClick(list: List<Double>) {
        val intent = Intent(this, MainActivity::class.java)
            .apply { putExtra("latitude", list[0])
                     putExtra("lontitude", list[1])}
        startActivity(intent)
    }

}