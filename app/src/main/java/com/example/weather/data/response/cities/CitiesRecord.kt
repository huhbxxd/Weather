package com.example.weather.data.response.cities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CitiesRecord(
    @SerializedName("fields")
    val cityFields: CitiesFields? = null
): Serializable