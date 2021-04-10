package com.example.weather.screens.cities

import android.Manifest
import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.App
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.data.cities.CitiesFields
import com.example.weather.data.cities.CitiesRecord
import com.example.weather.screens.choose.ChooseActivity
import com.example.weather.screens.cities.di.CitiesModule
import com.example.weather.screens.cities.di.DaggerCitiesComponent
import com.example.weather.screens.cities.ui.SearchCitiesAdapter
import com.example.weather.screens.cities.ui.StoredCitiesAdapter
import com.example.weather.screens.main.MainActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_cities.*
import javax.inject.Inject

class CitiesActivity: BaseActivity(){

    companion object {
        const val DIRECTION_BOT = 1
    }

    override val layout: Int
        get() = R.layout.activity_cities

    private lateinit var linearLayoutManagerSearchCities: LinearLayoutManager
    private lateinit var linearLayoutManagerListCities: LinearLayoutManager
    private lateinit var adapterSearchSearchCities: SearchCitiesAdapter
    private lateinit var adapterStoredCities: StoredCitiesAdapter
    private lateinit var queryText: String
    private lateinit var sharedPreferencesListCities: SharedPreferences
    private lateinit var sharedPreferencesLastCity: SharedPreferences

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
        searchView.setOnSearchClickListener {
            recyclerViewSearchCities.visibility = View.VISIBLE
            recyclerViewListCities.visibility = View.GONE
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
        searchView.setOnCloseListener {
            adapterSearchSearchCities.citiesClear()
            recyclerViewSearchCities.visibility = View.GONE
            recyclerViewListCities.visibility = View.VISIBLE
            false // false -> override default setOnCloseListener
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferencesListCities = getSharedPreferences(MainActivity.LIST_CITIES, Context.MODE_PRIVATE)
        sharedPreferencesLastCity = getSharedPreferences(MainActivity.LAST_CITY, Context.MODE_PRIVATE)

        linearLayoutManagerSearchCities = LinearLayoutManager(this)
        linearLayoutManagerListCities = LinearLayoutManager(this)
        recyclerViewSearchCities.layoutManager = linearLayoutManagerSearchCities
        recyclerViewListCities.layoutManager = linearLayoutManagerListCities

        component.inject(this)

        adapterSearchSearchCities = SearchCitiesAdapter(::onItemClick)
        recyclerViewSearchCities.adapter = adapterSearchSearchCities
        adapterStoredCities = StoredCitiesAdapter(::onItemClick)
        recyclerViewListCities.adapter = adapterStoredCities

        viewModel.searchCitiesViewModel.observe(this, Observer {
            adapterSearchSearchCities.hasLoading = it.second
            adapterSearchSearchCities.listCities = it.first as MutableList<CitiesRecord>
        })

        val  requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                val intent = Intent(this, MainActivity::class.java)
                    .apply {
                        putExtra(MainActivity.FAB_CLICKED, true)
                    }
                if (isGranted) {
                    onStartedBefore()
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Location denied", Toast.LENGTH_SHORT).show()
                }
            }

        fabLocation.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        recyclerViewSearchCities.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(DIRECTION_BOT)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                    && adapterSearchSearchCities.hasLoading) {
                    viewModel.onNextPage(queryText)
                    adapterSearchSearchCities.hasLoading = false
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.listCitiesViewModel.observe(this, Observer {
            adapterStoredCities.listCities = it
        })
    }

    // user choose city from search list and click
    private fun onItemClick(city: CitiesFields) {
        onStartedBefore()
        val intent = Intent(this, MainActivity::class.java)
            .apply {
                addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                putExtra(MainActivity.FAB_CLICKED, false)
            }
        // serializable gson to json to next time deserializable
        val jsonCity = Gson().toJson(city)
            saveCity(jsonCity)
        startActivity(intent)
    }

    private fun saveCity(value: String) {
        val setFromSharedPreferences = sharedPreferencesListCities.getStringSet(MainActivity.LIST_CITIES, mutableSetOf())
        val copyOfSet = setFromSharedPreferences?.toMutableSet()
            .apply {
                this?.add(value)
            }
        sharedPreferencesListCities.edit()
            .apply{
                putStringSet(MainActivity.LIST_CITIES, copyOfSet) // save city to store
                apply()
        }
        sharedPreferencesLastCity.edit()
            .apply {
                putString(MainActivity.LAST_CITY, value) // save city like last city that was chosen before close app
                apply()
        }
    }




}