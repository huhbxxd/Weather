package com.example.weather.data.repository

import com.example.weather.data.WeatherData
import io.reactivex.Single

interface WeatherRepository {

    fun loadWeatherByName(cityName: String): Single<List<WeatherData>>

    fun loadWeatherByCoord(lat: Double, lon: Double): Single<List<WeatherData>>

}