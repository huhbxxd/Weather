package com.example.weather.screens.main

import android.location.Location
import com.example.weather.core.base.BaseInteractor
import com.example.weather.data.repository.CoordRepository
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainInteractor(private val repository: WeatherRepository,
                     private val coordRepository: CoordRepository
): BaseInteractor() {

    fun subscribeOnWeatherDailyByCoord(onSuccess: (DailyWeatherMain) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(Single.fromCallable { coordRepository.getLocation()
            .subscribeOn(Schedulers.io())
            .flatMap { repository.loadWeatherDailyByCoord(it).subscribeOn(Schedulers.io())} }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({it.subscribe(onSuccess, onError)}, onError))
    }

    /*    fun subscribeOnWeatherTodayByName(cityName: String, onSuccess: (weatherToday: TodayWeather) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(repository.loadWeatherDailyByName(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError))
    } */

}