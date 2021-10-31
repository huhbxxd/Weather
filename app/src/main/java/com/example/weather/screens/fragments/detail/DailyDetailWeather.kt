package com.example.weather.screens.fragments.detail

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.core.base.BaseActivity
import com.example.weather.data.response.weather.daily.day.DailyDayWeather
import kotlin.math.roundToInt

class DailyDetailWeather: BaseActivity() {

    companion object {
        const val ICON_URL = "http://openweathermap.org/img/wn/"
        const val INST_EXTRA = "INST_EXTRA"
        const val PATTERN_DAY = "EEEE" // "EEEE" it's full name day of week from table of SimpleDateFormat
        const val PATTERN_TIME = "HH:mm"
    }

    private lateinit var dayWeather: DailyDayWeather

    override val layout: Int
        get() = R.layout.activity_detail_weather_daily

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dayWeather = intent.getSerializableExtra(INST_EXTRA) as DailyDayWeather

//        Glide.with(this)
//            .load(R.drawable.sunrise)
//            .into(sunriseView)
//        Glide.with(this)
//            .load(R.drawable.sunset)
//            .into(sunsetView)
//        Glide.with(this)
//            .load(ICON_URL
//                    + dayWeather.weather[0].icon
//                    + ".png")
//            .into(weatherDayView)
//
//        with(dayWeather) {
//            dayOfWeek.text = dateFormatter(dt, PATTERN_DAY)
//            descriptionDay.text = weather[0].description
//
//            sunriseTime.text = dateFormatter(sunrise, PATTERN_TIME)
//            sunsetTime.text = dateFormatter(sunset, PATTERN_TIME)
//
//            with(temperature) {
//                tempDayWeek.text = day.roundToInt().toString()
//                tempNightWeek.text = night.roundToInt().toString()
//                mornTemperature.text = morn.roundToInt().toString()
//                eveTemperature.text = eve.roundToInt().toString()
//            }
//
//            with(feelsLike) {
//                tempFeelsDay.text = day.roundToInt().toString()
//                tempFeelsNight.text = night.roundToInt().toString()
//                tempFeelsMorn.text = morn.roundToInt().toString()
//                tempFeelsEve.text = eve.roundToInt().toString()
//            }
//
//            windSpeedValue.text = windSpeed.toString()
//            windDegValue.text = windDeg.toString()
//            cloudinessValue.text = clouds.toString()
//            uvIndexValue.text = uvi.toString()
//            popValue.text = pop.toString()
//            humidityValue.text = humidity.toString()
//            pressureValue.text = pressure.toString()
//            dewPointValue.text = dewPoint.toString()
//        }
    }

}