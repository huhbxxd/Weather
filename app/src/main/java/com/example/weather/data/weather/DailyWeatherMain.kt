package com.example.weather.data.weather

import com.example.weather.data.weather.current.CurrentWeather
import com.example.weather.data.weather.daily.daily_day.DailyDayWeather
import com.example.weather.data.weather.daily.daily_hour.HourlyWeather
import com.google.gson.annotations.SerializedName

class DailyWeatherMain {

    @SerializedName("lat")
    val lat: Double? = null

    @SerializedName("lon")
    val lon: Double? = null

    @SerializedName("timezone")
    val timezone: String? = null

    @SerializedName("timezone_offset")
    val timezoneOffset: Int? = null

    @SerializedName("current")
    val current: CurrentWeather? = null

    @SerializedName("daily")
    val daily: List<DailyDayWeather>? = null

    @SerializedName("houtly")
    val hourly: List<HourlyWeather>? = null

}