package com.example.weather.data.weather.daily.daily_hour

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class HourlyWeather: Serializable {

    @SerializedName("dt")
    val dt: Int? = null

    @SerializedName("temp")
    val temperature: Double? = null

    @SerializedName("feels_like")
    val feelsLike: Double? = null

    @SerializedName("pressure")
    val pressure: Int? = null

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

    @SerializedName("wind_gust")
    val windGust: Double? = null

    @SerializedName("weather")
    val weather: List<HourlyWeatherIcon>? = null

    @SerializedName("pop")
    val pop: Double? = null

}