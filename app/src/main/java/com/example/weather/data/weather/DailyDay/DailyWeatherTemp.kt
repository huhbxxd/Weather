package com.example.weather.data.weather.DailyDay

import com.google.gson.annotations.SerializedName

class DailyWeatherTemp {

    @SerializedName("day")
    val day: Double? = null

    @SerializedName("min")
    val min: Double? = null

    @SerializedName("max")
    val max: Double? = null

    @SerializedName("night")
    val night: Double? = null

    @SerializedName("eve")
    val eve: Double? = null

    @SerializedName("morn")
    val morn: Double? = null

}