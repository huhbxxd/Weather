package com.example.weather.screens.main

import com.example.weather.core.base.BaseInteractor
import com.example.weather.data.cities.CitiesFields
import com.example.weather.data.repositories.location.LocationRepository
import com.example.weather.data.repositories.stored.StoredCitiesRepository
import com.example.weather.data.repositories.weather.WeatherRepository
import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainInteractor(private val repository: WeatherRepository,
                     private val locationRepository: LocationRepository,
                     private val repositoryStored: StoredCitiesRepository
): BaseInteractor() {

    fun subscribeOnWeatherDailyByLocation(onSuccess: (DailyWeatherMain) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(Maybe.fromCallable {
            locationRepository.getLocation()
                .flatMap { repository.loadWeatherDailyByLocation(it)
                .subscribeOn(Schedulers.io()) }
        } // network call must not be in a main thread
            .subscribeOn(Schedulers.io())
            .doOnError(onError)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {it.subscribe(onSuccess, onError)})
    }

    fun subscribeOnWeatherDailyByCoord(onSuccess: (DailyWeatherMain) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(Maybe.fromCallable {
            repositoryStored.getLastCity()
                .flatMap { repository.loadWeatherDailyByCoord(it.coordCity?.get(0)!!, it.coordCity[1])
                .subscribeOn(Schedulers.io()) }
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(onError)
                .subscribe{it.subscribe(onSuccess, onError)})
    }
}