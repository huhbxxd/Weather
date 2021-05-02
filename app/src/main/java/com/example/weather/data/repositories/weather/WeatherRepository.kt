package com.example.weather.data.repositories.weather

import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Single

interface WeatherRepository {

    fun loadWeather(lat: Double, lon: Double): Single<DailyWeatherMain>

}