package com.example.weather.data.repositories.cities

import com.example.weather.data.CitiesApi
import com.example.weather.data.cities.Cities
import io.reactivex.Single


class CitiesRepositoryImpl(private val service: CitiesApi):
    CitiesRepository {

    private companion object {
        val DATASET = "geonames-all-cities-with-a-population-1000"
        val ROWS = 30
    }

    override fun getListCities(q: String, page: Int): Single<Cities> {

        return service.getCities(
            DATASET, q, page,
            ROWS
        )
    }

}