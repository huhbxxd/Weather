package com.example.weather.screens.main

import com.example.weather.data.response.cities.CitiesFields
import com.example.weather.data.repositories.location.LocationRepository
import com.example.weather.data.repositories.stored.StoredCitiesRepository
import com.example.weather.data.repositories.weather.WeatherRepository
import com.example.weather.data.response.weather.DailyWeatherMain

class MainInteractor(private val repository: WeatherRepository,
                     private val locationRepository: LocationRepository,
                     private val repositoryStored: StoredCitiesRepository
) {

    fun subscribeOnWeatherDailyByLocation(onSuccess: (DailyWeatherMain) -> Unit, onError: (Throwable) -> Unit) {
//        disposable.add(
//            locationRepository.getLocation()
//                .flatMap { repository.loadWeather(it.latitude, it.longitude) }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(onSuccess, onError))
    }

    fun subscribeOnWeatherDailyByCoord(onSuccess: (DailyWeatherMain) -> Unit, onError: (Throwable) -> Unit) {
//        disposable.add(
//            repositoryStored.getLastCity()
//                .flatMap {
//                    it.coordCity?.let { coordList ->
//                        repository.loadWeather(coordList[0], coordList[1])
//                    }
//                }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(onSuccess, onError))
    }

    fun getLastCityName(onComplete: (CitiesFields) -> Unit, onError: (Throwable) -> Unit) {
//        disposable.add(repositoryStored.getLastCity()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(onComplete, onError))
    }
}