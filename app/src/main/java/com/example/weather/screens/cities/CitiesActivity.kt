package com.example.weather.screens.cities

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.App
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.data.cities.CitiesFields
import com.example.weather.data.cities.CitiesRecord
import com.example.weather.screens.cities.di.CitiesModule
import com.example.weather.screens.cities.di.DaggerCitiesComponent
import com.example.weather.screens.cities.ui.CitiesAdapter
import com.example.weather.screens.main.MainActivity
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_cities.*
import javax.inject.Inject

class CitiesActivity: BaseActivity(){

    companion object {
        const val DIRECTION_BOT = 1
    }

    override val layout: Int
        get() = R.layout.activity_cities

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapterCities: CitiesAdapter
    private lateinit var queryText: String
    private lateinit var sharedPreferences: SharedPreferences
    private val citiesList = hashSetOf<String>()

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
                queryText = query!!
                viewModel.onSearch(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                queryText = newText!!
                viewModel.onSearch(newText)
                return true
            }

        })
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(MainActivity.LIST_CITIES, Context.MODE_PRIVATE)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewCities.layoutManager = linearLayoutManager

        component.inject(this)

        adapterCities = CitiesAdapter(::onItemClick)
        recyclerViewCities.adapter = adapterCities

        recyclerViewCities.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(DIRECTION_BOT)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                    && adapterCities.hasLoading) {
                    viewModel.onNextPage(queryText)
                    adapterCities.hasLoading = false
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.citiesViewModel.observe(this, Observer {
            adapterCities.hasLoading = it.second
            adapterCities.listCities = it.first
        })
    }

    private fun onItemClick(city: CitiesFields) {
        val list = city.coordCity!!
        val intent = Intent(this, MainActivity::class.java)
            .apply { putExtra(MainActivity.LATITUDE_EXTRA, list[0])
                     putExtra(MainActivity.LONTITUDE_EXTRA, list[1])
                     putExtra(MainActivity.CITY_NAME_EXTRA, city.cityName)}
        // serializable gson to json to next time deserializable in ListCitiesActivity
        citiesList.add(Gson().toJson(city))
            saveCity(citiesList)
        startActivity(intent)
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveCity(value: HashSet<String>) {
        sharedPreferences.edit().apply{
            putStringSet(MainActivity.LIST_CITIES, value)
            apply()
        }
    }

}