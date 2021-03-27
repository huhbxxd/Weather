package com.example.weather.screens.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.App
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.data.repository.CoordRepositoryImpl
import com.example.weather.screens.main.ui.adapter.MainAdapterDailyDay
import com.example.weather.screens.main.ui.adapter.MainAdapterDailyHour
import com.example.weather.screens.main.di.DaggerMainComponent
import com.example.weather.screens.main.di.MainModule
import kotlinx.android.synthetic.main.motion_layout.*
import kotlinx.android.synthetic.main.scroll_content.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainActivity: BaseActivity(R.layout.activity_main) {

    private companion object {
        val REQUEST_CODE = 1 // random value for permission
    }

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
        linearLayoutManagerDailyDay = LinearLayoutManager(this)
        linearLayoutManagerDailyHour = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewDailyDay.layoutManager = linearLayoutManagerDailyDay
        recyclerViewDailyDay.suppressLayout(true)
        recyclerViewDailyHour.layoutManager = linearLayoutManagerDailyHour

        component.inject(this)

        val permissionState = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (permissionState == PackageManager.PERMISSION_GRANTED) {
            viewModel.serviceLocation.observe(this, Observer {
                viewModel.latitude = it.latitude
                viewModel.longitude = it.longitude
            })
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
        }

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