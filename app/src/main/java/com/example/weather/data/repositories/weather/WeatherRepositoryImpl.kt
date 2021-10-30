package com.example.weather.data.repositories.weather

import com.example.weather.data.WeatherApi
import com.example.weather.data.response.weather.DailyWeatherMain
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherApi
) : WeatherRepository {

    override suspend fun loadWeather(lat: Double, lon: Double): DailyWeatherMain {
        return service.getWeatherDaily(
            lat = lat,
            lon = lon,
            exclude = EXCLUDE,
            units = UNITS,
            appid = API_KEY
        )
    }

    private companion object {
        const val API_KEY = "31cc76a1f3f18df21c60f3e30f9eae8f"
        const val EXCLUDE = "minutely"
        const val UNITS = "metric"
    }
}