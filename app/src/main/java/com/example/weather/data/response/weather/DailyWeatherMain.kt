package com.example.weather.data.response.weather

import com.example.weather.data.response.weather.current.CurrentWeather
import com.example.weather.data.response.weather.daily.day.DailyDayWeather
import com.example.weather.data.response.weather.daily.hour.HourlyWeather
import com.google.gson.annotations.SerializedName

data class DailyWeatherMain (
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val current: CurrentWeather,
    val daily: List<DailyDayWeather>,
    val hourly: List<HourlyWeather>,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int
)