package com.example.weather.data.repositories.cities

import com.example.weather.data.CitiesApi
import com.example.weather.data.response.cities.Cities
import io.reactivex.Single
import javax.inject.Inject


class CitiesRepositoryImpl @Inject constructor(
    private val service: CitiesApi
) : CitiesRepository {

    override suspend fun getListCities(q: String, page: Int): Cities {
        return service.getCities(
            query = q, page = page
        )
    }

}