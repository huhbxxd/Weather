package com.example.weather.data.response.cities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cities(
    @SerializedName("records")
    val citiesRecords: List<CitiesRecord>? = null
): Serializable