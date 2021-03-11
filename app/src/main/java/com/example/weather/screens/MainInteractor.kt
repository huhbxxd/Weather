package com.example.weather.screens

import com.example.weather.core.base.BaseInteractor
import com.example.weather.data.WeatherData
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.utils.Workers
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import java.util.*

class MainInteractor(workers: Workers,
                     private val repository: WeatherRepository): BaseInteractor(workers) {

    fun subscribeOnWeatherByName(cityName: String ,onSuccess: (list: List<WeatherData>) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(repository.loadWeatherByName(cityName)
            .schedule()
            .subscribe(onSuccess, onError))
    }

    fun subscribeOnWeatherByCoord(lat: Double, lon: Double, onSuccess: (list: List<WeatherData>) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(repository.loadWeatherByCoord(lat, lon)
            .schedule()
            .subscribe(onSuccess, onError))
    }

}