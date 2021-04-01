package com.example.weather.data.cities

import com.google.gson.annotations.SerializedName

class CitiesRecord {

    @SerializedName("fields")
    val cityFields: CitiesFields? = null

    @SerializedName("geometry")
    val cityGeometry: CitiesGeometry? = null

}