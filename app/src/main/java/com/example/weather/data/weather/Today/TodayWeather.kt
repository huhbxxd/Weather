package com.example.weather.data.weather.Today

import com.google.gson.annotations.SerializedName

class TodayWeather {

    @SerializedName("coord")
    val coord: TodayCoord? = null

    @SerializedName("weather")
    val todayWeatherIcon: List<TodayWeatherIcon>? = null

    @SerializedName("main")
    val weatherMainTodayWeather: TodayWeatherMain? = null

    @SerializedName("wind")
    val wind: TodayWind? = null

    @SerializedName("clouds")
    val clouds: TodayClouds? = null

    @SerializedName("sys")
    val sys: TodaySys? = null

    @SerializedName("timezone")
    val timezone: Int? = null

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("cod")
    val cod: Int? = null

    @SerializedName("dt")
    val currentTime: Int? = null

    @SerializedName("visibility")
    val visibility: Int? = null

    @SerializedName("base")
    val base: String? = null

}