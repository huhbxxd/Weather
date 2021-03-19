package com.example.weather.data.weather.Today

import com.google.gson.annotations.SerializedName

class TodayWeatherMain {

    @SerializedName("temp")
    val temperature: Double? = null

    @SerializedName("feels_like")
    val feelsLike: Double? = null

    @SerializedName("temp_min")
    val tempMin: Double? = null

    @SerializedName("temp_max")
    val tempMax: Double? = null

    @SerializedName("pressure")
    val pressure: Int? = null

    @SerializedName("humidity")
    val humidity: Int? = null


}