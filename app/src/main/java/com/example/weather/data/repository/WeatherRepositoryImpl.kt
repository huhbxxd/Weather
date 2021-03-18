package com.example.weather.data.repository

import com.example.weather.data.weather.Weather
import com.example.weather.data.WeatherApi
import io.reactivex.Single

class WeatherRepositoryImpl(private val service: WeatherApi): WeatherRepository {

    private companion object {
        val API_KEY = "31cc76a1f3f18df21c60f3e30f9eae8f"
    }

    override fun loadWeatherByName(cityName: String): Single<List<Weather>> {
        return service.getWeatherByName(cityName, API_KEY)
    }

    override fun loadWeatherByCoord(lat: Double, lon: Double): Single<List<Weather>> {
        return service.getWeatherByCoord(lat, lon, API_KEY)
    }




}