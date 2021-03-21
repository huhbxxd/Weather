package com.example.weather.data.repository

import com.example.weather.data.weather.daily_day.DailyWeatherMain
import com.example.weather.data.weather.today.TodayWeather
import io.reactivex.Single

interface WeatherRepository {

    fun loadWeatherTodayByName(cityName: String): Single<TodayWeather>

    fun loadWeatherTodayByCoord(lat: Double, lon: Double): Single<TodayWeather>

    fun loadWeatherDailyByCoord(lat: Double, lon: Double): Single<DailyWeatherMain>

}