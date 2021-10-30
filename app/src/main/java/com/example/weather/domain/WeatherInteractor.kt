package com.example.weather.domain

import com.example.weather.core.coroutines.CoroutineUseCase
import com.example.weather.data.repositories.weather.WeatherRepository
import com.example.weather.data.response.weather.DailyWeatherMain
import javax.inject.Inject

class WeatherInteractor @Inject constructor(
    private val repository: WeatherRepository
): CoroutineUseCase<DailyWeatherMain?, WeatherInteractor.Params>() {

    override suspend fun executeOnBackground(params: Params?): DailyWeatherMain? {
        return params?.let {
            repository.loadWeather(
                lat = it.lat,
                lon = it.lon
            )
        }
    }

    data class Params(
        val lat: Double,
        val lon: Double
    )

}