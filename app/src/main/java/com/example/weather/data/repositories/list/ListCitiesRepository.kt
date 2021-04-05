package com.example.weather.data.repositories.list

import com.example.weather.data.cities.CitiesFields
import io.reactivex.Single

interface ListCitiesRepository {

    fun getListCities(): Single<MutableList<CitiesFields>>

}