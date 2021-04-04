package com.example.weather.data.weather.daily.daily_day

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DailyDayWeatherIcon: Serializable {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("main")
    val mainWeatherIcon: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("icon")
    val icon: String? = null

}