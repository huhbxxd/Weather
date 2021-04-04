package com.example.weather.screens.main

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.App
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.screens.main.ui.adapters.MainAdapterDailyDay
import com.example.weather.screens.main.ui.adapters.MainAdapterDailyHour
import com.example.weather.screens.main.di.DaggerMainComponent
import com.example.weather.screens.main.di.MainModule
import kotlinx.android.synthetic.main.motion_layout.*
import kotlinx.android.synthetic.main.scroll_content.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class MainActivity: BaseActivity() {

    companion object {
        const val LATITUDE_EXTRA = "latitude"
        const val LONTITUDE_EXTRA = "lontitude"
        const val CITY_NAME_EXTRA = "CITY_NAME_EXTRA"
        const val defaultValue = 0.0
    }

    override val layout: Int
        get() = R.layout.activity_main

    private lateinit var linearLayoutManagerDailyDay: LinearLayoutManager
    private lateinit var linearLayoutManagerDailyHour: LinearLayoutManager
    private lateinit var adapterDailyDay: MainAdapterDailyDay
    private lateinit var adapterDailyHour: MainAdapterDailyHour
    private var permissionState by Delegates.notNull<Boolean>()

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

        permissionState = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        linearLayoutManagerDailyDay = LinearLayoutManager(this)
        linearLayoutManagerDailyHour = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewDailyDay.layoutManager = linearLayoutManagerDailyDay
        recyclerViewDailyDay.suppressLayout(true)
        recyclerViewDailyHour.layoutManager = linearLayoutManagerDailyHour

        component.inject(this)

        adapterDailyDay =
            MainAdapterDailyDay()
        recyclerViewDailyDay.adapter = adapterDailyDay
        adapterDailyHour =
            MainAdapterDailyHour()
        recyclerViewDailyHour.adapter = adapterDailyHour

        if (!permissionState) {
            with(intent) {
                viewModel.lat = getDoubleExtra(LATITUDE_EXTRA, defaultValue)
                viewModel.lon = getDoubleExtra(LONTITUDE_EXTRA, defaultValue)
            }
        } else {
            viewModel.permissionState = permissionState
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.weatherDailyLiveData.observe(this, Observer {
            with(it) {
                adapterDailyDay.listDailyDay = daily!!
                adapterDailyHour.listDailyHour = hourly!!

                when(permissionState) {
                    true -> cityName.text = locationFormatter(timezone!!)
                    false -> cityName.text = intent.getStringExtra(CITY_NAME_EXTRA)
                }

                description.text = current?.weatherIcon?.get(0)?.description
                currentTemp.text = current?.temp?.roundToInt().toString()

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

    private fun locationFormatter(s: String): String {
        return s.substringAfter("/") // from: America/Los_Angeles
            .replace("_", " ") // to: Los Angeles
    }
}