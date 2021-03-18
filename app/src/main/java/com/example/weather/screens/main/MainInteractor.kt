package com.example.weather.screens.main

import android.location.Location
import com.example.weather.core.base.BaseInteractor
import com.example.weather.data.weather.Weather
import com.example.weather.data.repository.CoordRepository
import com.example.weather.data.repository.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainInteractor(/* workers: Workers, */
                     private val repository: WeatherRepository,
                     private val coordRepository: CoordRepository
): BaseInteractor() {

    fun getCoordinates(onSuccess: (Location) -> Unit, onError: (Throwable) -> Unit){
        disposable.add(coordRepository.getLocation()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError))
    }

    fun subscribeOnWeatherByName(cityName: String, onSuccess: (list: List<Weather>) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(repository.loadWeatherByName(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError))
    }

    fun subscribeOnWeatherByCoord(lat: Double, lon: Double, onSuccess: (list: List<Weather>) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(repository.loadWeatherByCoord(lat, lon)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError))
    }

}