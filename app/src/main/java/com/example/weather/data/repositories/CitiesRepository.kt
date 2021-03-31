package com.example.weather.data.repositories

import com.example.weather.data.cities.Cities
import io.reactivex.Single

interface CitiesRepository {

    fun getListCities(q: String): Single<Cities>

}