package com.example.weather.data.weather.daily.daily_day

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class DailyDayWeather: Serializable {

    @SerializedName("dt")
    val dt: Int? = null

    @SerializedName("sunrise")
    val sunrise: Int? = null

    @SerializedName("sunset")
    val sunset: Int? = null

    @SerializedName("temp")
    val temperature: DailyDayWeatherTemp? = null

    @SerializedName("feels_like")
    val feelsLike: DailyDayWeatherFeelsLike? = null

    @SerializedName("pressure")
    val pressure: Int? = null

    @SerializedName("humidity")
    val humidity: Int? = null

    @SerializedName("dew_point")
    val dewPoint: Double? = null

    @SerializedName("wind_speed")
    val windSpeed: Double? = null

    @SerializedName("wind_deg")
    val windDeg: Double? = null

    @SerializedName("weather")
    val weather: List<DailyDayWeatherIcon>? = null

    @SerializedName("clouds")
    val clouds: Int? = null

    @SerializedName("pop")
    val pop: Double? = null

    @SerializedName("uvi")
    val uvi: Double? = null


}