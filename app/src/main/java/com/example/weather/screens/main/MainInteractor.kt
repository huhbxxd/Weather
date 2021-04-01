package com.example.weather.screens.main

import com.example.weather.core.base.BaseInteractor
import com.example.weather.data.repositories.CoordRepository
import com.example.weather.data.repositories.WeatherRepository
import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainInteractor(private val repository: WeatherRepository,
                     private val coordRepository: CoordRepository
): BaseInteractor() {

    fun subscribeOnWeatherDailyByCoord(onSuccess: (DailyWeatherMain) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(Maybe.fromCallable { coordRepository.getLocation()
            .flatMap { repository.loadWeatherDailyByCoord(it)
                .subscribeOn(Schedulers.io())} } // network call must not be in a main thread
            .subscribeOn(Schedulers.io())
            .doOnError(onError)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {it.subscribe(onSuccess, onError)})
    }

    /*    fun subscribeOnWeatherTodayByName(cityName: String, onSuccess: (weatherToday: TodayWeather) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(repository.loadWeatherDailyByName(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError))
    } */

}