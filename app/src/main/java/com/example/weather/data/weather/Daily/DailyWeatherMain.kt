package com.example.weather.data.weather.Daily

import com.google.gson.annotations.SerializedName

class DailyWeatherMain {

    @SerializedName("lat")
    val lat: Double? = null

    @SerializedName("lon")
    val lon: Double? = null

    @SerializedName("timezone")
    val timezone: String? = null

    @SerializedName("timezone_offset")
    val timezone_offset: Int? = null

    @SerializedName("current")
    val current: DailyWeatherCurrent? = null

    @SerializedName("daily")
    val daily: List<DailyWeather>? = null

}