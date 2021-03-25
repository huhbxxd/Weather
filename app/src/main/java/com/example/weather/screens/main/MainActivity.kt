package com.example.weather.screens.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.App
import com.example.weather.R
import com.example.weather.screens.main.UI.adapter.MainAdapterDailyDay
import com.example.weather.screens.main.UI.adapter.MainAdapterDailyHour
import com.example.weather.screens.main.di.DaggerMainComponent
import com.example.weather.screens.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.motion_layout.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManagerDailyDay: LinearLayoutManager
    private lateinit var linearLayoutManagerDailyHour: LinearLayoutManager
    private lateinit var adapterDailyDay: MainAdapterDailyDay
    private lateinit var adapterDailyHour: MainAdapterDailyHour

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

        linearLayoutManagerDailyDay = LinearLayoutManager(this)
        linearLayoutManagerDailyHour = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewDailyDay.layoutManager = linearLayoutManagerDailyDay
        recyclerViewDailyDay.suppressLayout(true)
        recyclerViewDailyHour.layoutManager = linearLayoutManagerDailyHour

        component.inject(this)

        viewModel.loadGeoPosition()
        adapterDailyDay =
            MainAdapterDailyDay()
        recyclerViewDailyDay.adapter = adapterDailyDay
        adapterDailyHour =
            MainAdapterDailyHour()
        recyclerViewDailyHour.adapter = adapterDailyHour
    }

    override fun onResume() {
        super.onResume()
        viewModel.weatherDailyLiveData.observe(this, Observer { with(it) {
            adapterDailyDay.listDailyDay = daily!!
            adapterDailyHour.listDailyHour = hourly!!

            cityName.text = timezone
            description.text = current?.weatherIcon?.get(0)?.description
            currentTemp.text = current?.temp.toString()

            sunriseTime.text = dateFormatter(current?.sunrise!!)
            sunsetTime.text = dateFormatter(current.sunset!!)
            pressureValue.text = current.pressure.toString()
            humidityValue.text = current.humidity.toString()
            feelsLikeValue.text = current.feelsLike.toString()
            cloudinessValue.text = current.clouds.toString()
            windSpeedValue.text = current.windSpeed.toString()
            uvIndexValue.text = current.uvi.toString()
          }
        })
    }

    @SuppressLint("SimpleDateFormat")
    private fun dateFormatter(unix: Int): String {
        return SimpleDateFormat("HH:mm") // "HH:mm" hours:minutes from table of SimpleDateFormat
            .format(Date(unix.toLong() * 1000))
    }
}