package com.example.weather.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.App
import com.example.weather.R
import com.example.weather.screens.main.UI.MainAdapterDailyDay
import com.example.weather.screens.main.di.DaggerMainComponent
import com.example.weather.screens.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapterDailyDay: MainAdapterDailyDay

    private val component by lazy {
        DaggerMainComponent.builder()
            .appComponent((application as App).appComponent)
            .mainModule(MainModule(this))
            .build()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        component.inject(this)

        viewModel.loadGeoPosition()
        viewModel.weatherDailyLiveData.observe(this, Observer {
            adapterDailyDay.listWeatherDaily = it
        })

        adapterDailyDay = MainAdapterDailyDay()
        recyclerView.adapter = adapterDailyDay


    }

}