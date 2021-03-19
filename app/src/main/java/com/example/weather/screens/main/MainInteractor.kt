package com.example.weather.screens.main

import android.location.Location
import com.example.weather.core.base.BaseInteractor
import com.example.weather.data.repository.CoordRepository
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.data.weather.Daily.DailyWeatherMain
import com.example.weather.data.weather.Today.TodayWeather
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

    fun subscribeOnWeatherTodayByName(cityName: String, onSuccess: (weatherToday: TodayWeather) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(repository.loadWeatherTodayByName(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError))
    }

    fun subscribeOnWeatherTodayByCoord(lat: Double, lon: Double, onSuccess: (weatherToday: TodayWeather) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(repository.loadWeatherTodayByCoord(lat, lon)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError))
    }

    fun subscribeOnWeatherDailyByCoord(lat: Double, lon: Double, onSuccess: (weatherDaily: DailyWeatherMain) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(repository.loadWeatherDailyByCoord(lat, lon)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError))
    }

}