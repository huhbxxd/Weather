package com.example.weather.data.weather

import com.google.gson.annotations.SerializedName

class Weather {

    @SerializedName("coord")
    val coord: Coord? = null

    @SerializedName("weather")
    private val weather: List<Weather>? = null

    @SerializedName("main")
    private val main: Main? = null

    @SerializedName("wind")
    private val wind: Wind? = null

    @SerializedName("clouds")
    private val clouds: Clouds? = null

    @SerializedName("sys")
    private val sys: Sys? = null

    @SerializedName("timezone")
    private val timezone: Int? = null

    @SerializedName("id")
    private val id: Int? = null

    @SerializedName("name")
    private val name: String? = null

    @SerializedName("cod")
    private val cod: Int? = null

    @SerializedName("temp")
    val temperature: Double? = null

    @SerializedName("dt")
    private val currentTime: Int? = null

    @SerializedName("visibility")
    private val visibility: Int? = null

    @SerializedName("base")
    private val base: String? = null

}