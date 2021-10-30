package com.example.weather.data.repositories.stored

import com.example.weather.data.response.cities.CitiesFields
import io.reactivex.Single

interface StoredCitiesRepository {

    suspend fun getListCities(): Single<List<CitiesFields>>

    suspend fun getLastCity(): Single<CitiesFields>

}