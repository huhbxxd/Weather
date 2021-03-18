package com.example.weather.data.repository

import com.example.weather.data.weather.Weather
import io.reactivex.Single

interface WeatherRepository {

    fun loadWeatherByName(cityName: String): Single<List<Weather>>

    fun loadWeatherByCoord(lat: Double, lon: Double): Single<List<Weather>>

}