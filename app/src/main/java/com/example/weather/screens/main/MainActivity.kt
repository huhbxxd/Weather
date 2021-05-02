package com.example.weather.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.App
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.data.weather.DailyWeatherMain
import com.example.weather.data.weather.daily.day.DailyDayWeather
import com.example.weather.data.weather.daily.hour.HourlyWeather
import com.example.weather.screens.choose.ChooseActivity
import com.example.weather.screens.cities.CitiesActivity
import com.example.weather.screens.detail.DailyDetailWeather
import com.example.weather.screens.detail.HourlyDetailWeather
import com.example.weather.screens.main.ui.adapters.MainAdapterDailyDay
import com.example.weather.screens.main.ui.adapters.MainAdapterDailyHour
import com.example.weather.screens.main.di.DaggerMainComponent
import com.example.weather.screens.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.motion_layout.*
import kotlinx.android.synthetic.main.scroll_content.*
import java.io.Serializable
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class MainActivity: BaseActivity() {

    companion object {
        const val STATE_LOAD = "STATE_LOAD"
        const val PATTERN_TIME = "HH:mm" // "HH:mm" hours:minutes from table of SimpleDateFormat
        const val LIST_CITIES = "LIST_CITIES"
        const val LAST_CITY = "LAST_CITY"
        const val STARTED_BEFORE = "STARTED_BEFORE"
    }

    override val layout: Int
        get() = R.layout.activity_main

    private lateinit var adapterDailyDay: MainAdapterDailyDay
    private lateinit var adapterDailyHour: MainAdapterDailyHour
    private var permissionLocation by Delegates.notNull<Boolean>()
    var stateLoad by Delegates.notNull<Boolean>()

    private val component by lazy {
        DaggerMainComponent.builder()
            .appComponent((application as App).appComponent)
            .mainModule(MainModule(this))
            .build()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // default true to try load by location ( if permission was accept )
        // else loading city from search list
        // look onResume to this
        stateLoad = intent.getBooleanExtra(STATE_LOAD, true)
        permissionLocation = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        recyclerViewDailyDay.layoutManager =
            LinearLayoutManager(this)
        recyclerViewDailyDay.suppressLayout(true)
        recyclerViewDailyHour.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        component.inject(this)

        adapterDailyDay =
            MainAdapterDailyDay(::onItemClick)
        recyclerViewDailyDay.adapter = adapterDailyDay
        adapterDailyHour =
            MainAdapterDailyHour(::onItemClick)
        recyclerViewDailyHour.adapter = adapterDailyHour

        floatingCitiesList.setOnClickListener {
            val intent = Intent(this, CitiesActivity::class.java)
                .apply { addFlags(FLAG_ACTIVITY_CLEAR_TOP) }
            startActivity(intent)
        }

        viewModel.weatherThrowable.observe(this, Observer {
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT)
        })
    }

    override fun onResume() {
        super.onResume()
        checkStartedBefore()
        // if permission was denied, starting application load last chosen city
        // try load by location
        if(stateLoad && permissionLocation) {
            viewModel.weatherLiveDataLocation.observe(this, Observer {
                onLoadedWeather(it)
                cityName.text = locationFormatter(it.timezone!!)
            })
        // else load by city from search list or stored list
        } else {
            viewModel.weatherLiveDataByCity.observe(this, Observer {
                onLoadedWeather(it)
            })
            // loading name from local storage (stored cities, last city)
            // because weather api give not correct city name by location
            viewModel.cityNameLiveData.observe(this, Observer {
                cityName.text = it
            })
        }

    }

    private fun onLoadedWeather(weather: DailyWeatherMain) = with(weather) {
        adapterDailyDay.listDailyDay = daily
        adapterDailyHour.listDailyHour = hourly
        description.text = current.weatherIcon[0].description
        currentTemp.text = current.temp.roundToInt().toString()
        sunriseTime.text = dateFormatter(current.sunrise, PATTERN_TIME)
        sunsetTime.text = dateFormatter(current.sunset, PATTERN_TIME)
        pressureValue.text = current.pressure.toString()
        humidityValue.text = current.humidity.toString()
        feelsLikeValue.text = current.feelsLike.toString()
        cloudinessValue.text = current.clouds.toString()
        windSpeedValue.text = current.windSpeed.toString()
        uvIndexValue.text = current.uvi.toString()
    }

    private fun checkStartedBefore() {
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        if (!sharedPreferences.getBoolean(STARTED_BEFORE, false)) {
            startActivity(Intent(this, ChooseActivity::class.java))
            finish()
        }
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

    private fun locationFormatter(s: String): String {
        return s.substringAfter("/") // from: America/Los_Angeles
            .replace("_", " ") // to: Los Angeles
    }


}
