package com.example.weather.data.weather.Daily

import com.google.gson.annotations.SerializedName

class DailyWeather {

    @SerializedName("dt")
    val dt: Int? = null

    @SerializedName("sunrise")
    val sunrise: Int? = null

    @SerializedName("sunset")
    val sunset: Int? = null

    @SerializedName("temp")
    val temperature: DailyWeatherTemp? = null

    @SerializedName("feels_like")
    val feels_like: DailyWeatherFeelsLike? = null

    @SerializedName("pressure")
    val pressure: Int? = null

    @SerializedName("humidity")
    val humidity: Int? = null

    @SerializedName("dew_point")
    val dew_point: Double? = null

    @SerializedName("wind_speed")
    val wind_speed: Double? = null

    @SerializedName("wind_deg")
    val wind_deg: Double? = null

    @SerializedName("weather")
    val weather: List<DailyWeatherIcon>? = null

    @SerializedName("clouds")
    val clouds: Int? = null

    @SerializedName("pop")
    val pop: Double? = null

    @SerializedName("uvi")
    val uvi: Double? = null

}