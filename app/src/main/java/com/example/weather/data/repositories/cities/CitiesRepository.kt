package com.example.weather.data.repositories.cities

import com.example.weather.data.response.cities.Cities
import io.reactivex.Single

interface CitiesRepository {

    suspend fun getListCities(q: String, page: Int): Cities

}