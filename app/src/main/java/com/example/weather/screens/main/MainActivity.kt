package com.example.weather.screens.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.App
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.data.cities.CitiesFields
import com.example.weather.data.weather.DailyWeatherMain
import com.example.weather.data.weather.daily.day.DailyDayWeather
import com.example.weather.data.weather.daily.hour.HourlyWeather
import com.example.weather.screens.cities.CitiesActivity
import com.example.weather.screens.detail.DailyDetailWeather
import com.example.weather.screens.detail.HourlyDetailWeather
import com.example.weather.screens.main.ui.adapters.MainAdapterDailyDay
import com.example.weather.screens.main.ui.adapters.MainAdapterDailyHour
import com.example.weather.screens.main.di.DaggerMainComponent
import com.example.weather.screens.main.di.MainModule
import kotlinx.android.synthetic.main.bottom_bar.*
import kotlinx.android.synthetic.main.motion_layout.*
import kotlinx.android.synthetic.main.scroll_content.*
import java.io.Serializable
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class MainActivity: BaseActivity() {

    companion object {
        var CITY_FIELD = "CITY_FIELD"
        const val defaultValue = 0.0
        const val PATTERN_TIME = "HH:mm" // "HH:mm" hours:minutes from table of SimpleDateFormat
        const val LIST_CITIES = "LIST_CITIES"
        const val LAST_CITY = "LAST_CITY"
    }

    override val layout: Int
        get() = R.layout.activity_main

    private lateinit var linearLayoutManagerDailyDay: LinearLayoutManager
    private lateinit var linearLayoutManagerDailyHour: LinearLayoutManager
    private lateinit var adapterDailyDay: MainAdapterDailyDay
    private lateinit var adapterDailyHour: MainAdapterDailyHour
    private var permissionState by Delegates.notNull<Boolean>()
    private lateinit var city: CitiesFields

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
//        city = intent.getSerializableExtra(CITY_FIELD) as CitiesFields

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
            MainAdapterDailyDay(::onItemClick)
        recyclerViewDailyDay.adapter = adapterDailyDay
        adapterDailyHour =
            MainAdapterDailyHour(::onItemClick)
        recyclerViewDailyHour.adapter = adapterDailyHour



//        // rework this
//        if (!permissionState) {
//
//        } else {
//            viewModel.permissionState = permissionState
//        }

        imageView.setOnClickListener {
            val intent = Intent(this, CitiesActivity::class.java)
            startActivity(intent)
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
//                    false -> cityName.text = city.cityName!!
                }
                description.text = current?.weatherIcon?.get(0)?.description
                currentTemp.text = current?.temp?.roundToInt().toString()
                sunriseTime.text = dateFormatter(current?.sunrise!!, PATTERN_TIME)
                sunsetTime.text = dateFormatter(current.sunset!!, PATTERN_TIME)
                pressureValue.text = current.pressure.toString()
                humidityValue.text = current.humidity.toString()
                feelsLikeValue.text = current.feelsLike.toString()
                cloudinessValue.text = current.clouds.toString()
                windSpeedValue.text = current.windSpeed.toString()
                uvIndexValue.text = current.uvi.toString()
            }
        })
    }

    private fun locationFormatter(s: String): String {
        return s.substringAfter("/") // from: America/Los_Angeles
            .replace("_", " ") // to: Los Angeles
    }

    private fun <T: Serializable> onItemClick(item: T) {
        var intent: Intent? = null
        when (item::class) {
            DailyDayWeather::class -> {
                intent = Intent(this, DailyDetailWeather::class.java)
                    .apply { putExtra(DailyDetailWeather.INST_EXTRA, item) }
            }
            HourlyWeather::class -> {
                intent = Intent(this, HourlyDetailWeather::class.java)
                    .apply { putExtra(HourlyDetailWeather.INST_EXTRA, item) }
            }
        }
        startActivity(intent)
    }

    private fun onLoadedWeather(weather: DailyWeatherMain) = with(weather) {
        adapterDailyDay.listDailyDay = daily!!
        adapterDailyHour.listDailyHour = hourly!!
        description.text = current?.weatherIcon?.get(0)?.description
        currentTemp.text = current?.temp?.roundToInt().toString()
        sunriseTime.text = dateFormatter(current?.sunrise!!, PATTERN_TIME)
        sunsetTime.text = dateFormatter(current.sunset!!, PATTERN_TIME)
        pressureValue.text = current.pressure.toString()
        humidityValue.text = current.humidity.toString()
        feelsLikeValue.text = current.feelsLike.toString()
        cloudinessValue.text = current.clouds.toString()
        windSpeedValue.text = current.windSpeed.toString()
        uvIndexValue.text = current.uvi.toString()
    }
}