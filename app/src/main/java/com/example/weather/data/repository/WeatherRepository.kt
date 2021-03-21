package com.example.weather.data.repository

import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Single

interface WeatherRepository {

    fun loadWeatherDailyByName(cityName: String): Single<DailyWeatherMain>

    fun loadWeatherDailyByCoord(lat: Double, lon: Double): Single<DailyWeatherMain>

}