package com.example.weather.domain.cities

import com.example.weather.core.coroutines.CoroutineUseCase
import com.example.weather.data.repositories.cities.CitiesRepository
import com.example.weather.data.response.cities.Cities
import com.example.weather.data.response.cities.CitiesFields
import com.example.weather.utils.requireParams
import javax.inject.Inject

class CitiesInteractor @Inject constructor(
    private val repository: CitiesRepository
): CoroutineUseCase<Cities, CitiesInteractor.Params>() {

    override suspend fun executeOnBackground(params: Params?): Cities {
        return requireParams(params) {
            repository.getListCities(
                q = query,
                page = page
            )
        }
    }

    data class Params(
        val query: String,
        val page: Int
    )

}