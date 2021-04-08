package com.example.weather.data.cities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CitiesRecord: Serializable {

    @SerializedName("fields")
    val cityFields: CitiesFields? = null

}