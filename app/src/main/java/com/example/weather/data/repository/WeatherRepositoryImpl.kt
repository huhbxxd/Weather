package com.example.weather.data.repository

import com.example.weather.data.WeatherApi
import com.example.weather.data.weather.DailyDay.DailyWeatherMain
import com.example.weather.data.weather.Today.TodayWeather
import io.reactivex.Single

class WeatherRepositoryImpl(private val service: WeatherApi): WeatherRepository {

    private companion object {
        val API_KEY = "31cc76a1f3f18df21c60f3e30f9eae8f"
        val EXCLUDE = "minutely,hourly"
    }

    override fun loadWeatherTodayByName(cityName: String): Single<TodayWeather> {
        return service.getWeatherTodayByName(cityName, API_KEY)
    }

    override fun loadWeatherTodayByCoord(lat: Double, lon: Double): Single<TodayWeather> {
        return service.getWeatherTodayByCoord(lat, lon, API_KEY)
    }

    override fun loadWeatherDailyByCoord(lat: Double, lon: Double): Single<DailyWeatherMain> {
        return service.getWeatherDailyByCoord(lat, lon, EXCLUDE, API_KEY)
    }


}