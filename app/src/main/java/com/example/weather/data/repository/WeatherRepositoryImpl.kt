package com.example.weather.data.repository

import android.location.Location
import com.example.weather.data.WeatherApi
import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class WeatherRepositoryImpl(private val service: WeatherApi): WeatherRepository {

    private companion object {
        val API_KEY = "31cc76a1f3f18df21c60f3e30f9eae8f"
        val EXCLUDE = "minutely"
        val UNITS = "metric"
    }

    override fun loadWeatherDailyByName(cityName: String): Single<DailyWeatherMain> {
        return service.getWeatherDailyByName(cityName, UNITS, API_KEY)
    }


    override fun loadWeatherDailyByCoord(location: Location): Single<DailyWeatherMain> {
        return service.getWeatherDailyByCoord(location.latitude, location.longitude, EXCLUDE, UNITS, API_KEY)
    }


}