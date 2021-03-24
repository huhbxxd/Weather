package com.example.weather.data.weather.current

import com.google.gson.annotations.SerializedName

class CurrentWeather {

    @SerializedName("dt")
    val dt: Int? = null

    @SerializedName("sunrise")
    val sunrise: Int? = null

    @SerializedName("sunset")
    val sunset: Int? = null

    @SerializedName("temp")
    val temp: Double? = null

    @SerializedName("feels_like")
    val feelsLike: Double? = null

    @SerializedName("pressure")
    val pressure: Double? = null

    @SerializedName("humidity")
    val humidity: Int? = null

    @SerializedName("dew_point")
    val dewPoint: Double? = null

    @SerializedName("uvi")
    val uvi: Double? = null

    @SerializedName("clouds")
    val clouds: Int? = null

    @SerializedName("visibility")
    val visibility: Int? = null

    @SerializedName("wind_speed")
    val windSpeed: Double? = null

    @SerializedName("wind_deg")
    val windDeg: Int? = null

    @SerializedName("weather")
    val weatherIcon: List<CurrentWeatherIcon>? = null

}