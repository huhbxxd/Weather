package com.example.weather.data.repositories.weather

import com.example.weather.data.response.weather.DailyWeatherMain
import io.reactivex.Single

interface WeatherRepository {

    suspend fun loadWeather(lat: Double, lon: Double): DailyWeatherMain

}