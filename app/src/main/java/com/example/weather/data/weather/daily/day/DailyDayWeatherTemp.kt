package com.example.weather.data.weather.daily.day

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DailyDayWeatherTemp (
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
): Serializable