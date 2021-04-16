package com.example.weather.data.repositories.weather

import com.example.weather.data.WeatherApi
import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Single

class WeatherRepositoryImpl(private val service: WeatherApi):
    WeatherRepository {

    private companion object {
        const val API_KEY = "31cc76a1f3f18df21c60f3e30f9eae8f"
        const val EXCLUDE = "minutely"
        const val UNITS = "metric"
    }

    override fun loadWeather(lat: Double, lon: Double): Single<DailyWeatherMain> {
        return service.getWeatherDaily(lat, lon,
            EXCLUDE,
            UNITS,
            API_KEY
        )
    }
}