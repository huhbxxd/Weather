package com.example.weather.data.weather

import com.google.gson.annotations.SerializedName

class Main {

    @SerializedName("temp")
    val temperature: Double? = null

    @SerializedName("feels_like")
    val feelsLike: Double? = null

    @SerializedName("temp_min")
    val tempMin: Double? = null

    @SerializedName("temp_max")
    val tempMax: Double? = null

    @SerializedName("coord")
    val coord: Coord? = null

    @SerializedName("pressure")
    val pressure: Int? = null

    @SerializedName("humidity")
    val humidity: Int? = null


}