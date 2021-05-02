package com.example.weather.screens.detail

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.data.weather.daily.hour.HourlyWeather
import kotlinx.android.synthetic.main.activity_detail_weather_hourly.*
import kotlin.math.roundToInt

class HourlyDetailWeather: BaseActivity() {

    companion object {
        const val ICON_URL = "http://openweathermap.org/img/wn/"
        const val INST_EXTRA = "INST_EXTRA"
        const val PATTERN_TIME = "HH:mm"
    }

    private lateinit var hourWeather: HourlyWeather

    override val layout: Int
        get() = R.layout.activity_detail_weather_hourly

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hourWeather = intent.getSerializableExtra(INST_EXTRA) as HourlyWeather

        Glide.with(this)
            .load(
                ICON_URL
                    + hourWeather.weather[0].icon
                    + ".png")
            .into(weatherHourView)


        with(hourWeather) {
            hourOfDay.text = dateFormatter(dt, PATTERN_TIME)
            descriptionHour.text = weather[0].description

            feelsLikeHourValue.text = feelsLike.roundToInt().toString()
            visibilityHourValue.text = visibility.toString()
            windSpeedHourValue.text = windSpeed.toString()
            windDegHourValue.text = windDeg.toString()
            cloudinessHourValue.text = clouds.toString()
            uvIndexHourValue.text = uvi.toString()
            popHourValue.text = pop.toString()
            humidityHourValue.text = humidity.toString()
            pressureHourValue.text = pressure.toString()
            dewPointHourValue.text = dewPoint.toString()
        }
    }
}
