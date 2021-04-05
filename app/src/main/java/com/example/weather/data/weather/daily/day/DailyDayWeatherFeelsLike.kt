package com.example.weather.data.weather.daily.day

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DailyDayWeatherFeelsLike: Serializable {

    @SerializedName("day")
    val day: Double? = null

    @SerializedName("night")
    val night: Double? = null

    @SerializedName("eve")
    val eve: Double? = null

    @SerializedName("morn")
    val morn: Double? = null

}