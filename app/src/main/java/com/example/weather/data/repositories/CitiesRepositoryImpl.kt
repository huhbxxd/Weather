package com.example.weather.data.repositories

import com.example.weather.data.CitiesApi
import com.example.weather.data.cities.Cities
import io.reactivex.Single


class CitiesRepositoryImpl(private val service: CitiesApi): CitiesRepository {

    private companion object {
        val DATASET = "geonames-all-cities-with-a-population-1000"
        val ROWS = 20
    }

    override fun getListCities(q: String): Single<Cities> {
        return service.getCities(DATASET, q, ROWS)
    }

}