package com.example.weather.data.weather.daily.daily_day

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DailyDayWeatherTemp: Serializable {

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