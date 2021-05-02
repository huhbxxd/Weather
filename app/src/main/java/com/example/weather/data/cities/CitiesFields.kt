package com.example.weather.data.cities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CitiesFields: Serializable {

    @SerializedName("name")
    val cityName: String? = null

    @SerializedName("timezone")
    val timeZone: String? = null

    @SerializedName("coordinates")
    val coordCity: List<Double>? = null


}
