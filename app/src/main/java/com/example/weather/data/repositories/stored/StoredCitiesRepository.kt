package com.example.weather.data.repositories.stored

import com.example.weather.data.cities.CitiesFields
import io.reactivex.Single

interface StoredCitiesRepository {

    fun getListCities(): Single<List<CitiesFields>>

    fun getLastCity(): Single<CitiesFields>

}