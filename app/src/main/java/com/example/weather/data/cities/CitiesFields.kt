package com.example.weather.data.cities

import com.google.gson.annotations.SerializedName

class CitiesFields {

    @SerializedName("name")
    val cityName: String? = null

    @SerializedName("timezone")
    val timeZone: String? = null

    @SerializedName("coordinates")
    val coordCity: List<Double>? = null


}