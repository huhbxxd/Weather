package com.example.weather.data.repositories.weather

import android.location.Location
import com.example.weather.data.WeatherApi
import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Single

class WeatherRepositoryImpl(private val service: WeatherApi):
    WeatherRepository {

    private companion object {
        val API_KEY = "31cc76a1f3f18df21c60f3e30f9eae8f"
        val EXCLUDE = "minutely"
        val UNITS = "metric"
    }

    override fun loadWeatherDailyByCoord(lat: Double, lon: Double): Single<DailyWeatherMain> {
        return service.getWeatherDailyByCoord(lat, lon,
            EXCLUDE,
            UNITS,
            API_KEY
        )
    }

    override fun loadWeatherDailyByLocation(location: Location): Single<DailyWeatherMain> {
        return service.getWeatherDailyByLocation(location.latitude, location.longitude,
            EXCLUDE,
            UNITS,
            API_KEY
        )
    }
}