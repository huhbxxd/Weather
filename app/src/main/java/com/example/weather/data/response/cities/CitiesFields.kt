package com.example.weather.data.response.cities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CitiesFields(
    @SerializedName("name")
    val cityName: String? = null,
    @SerializedName("timezone")
    val timeZone: String? = null,
    @SerializedName("coordinates")
    val coordCity: List<Double>? = null
): Serializable
