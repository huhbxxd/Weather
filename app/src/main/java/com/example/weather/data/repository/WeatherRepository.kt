package com.example.weather.data.repository

import com.example.weather.data.weather.DailyDay.DailyWeatherMain
import com.example.weather.data.weather.Today.TodayWeather
import io.reactivex.Single

interface WeatherRepository {

    fun loadWeatherTodayByName(cityName: String): Single<TodayWeather>

    fun loadWeatherTodayByCoord(lat: Double, lon: Double): Single<TodayWeather>

    fun loadWeatherDailyByCoord(lat: Double, lon: Double): Single<DailyWeatherMain>

}