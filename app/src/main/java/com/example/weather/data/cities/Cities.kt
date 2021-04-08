package com.example.weather.data.cities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Cities: Serializable {

    @SerializedName("records")
    val citiesRecords: List<CitiesRecord>? = null

}