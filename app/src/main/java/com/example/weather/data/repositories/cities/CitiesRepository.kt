package com.example.weather.data.repositories.cities

import com.example.weather.data.cities.Cities
import io.reactivex.Single

interface CitiesRepository {

    fun getListCities(q: String, page: Int): Single<Cities>

}