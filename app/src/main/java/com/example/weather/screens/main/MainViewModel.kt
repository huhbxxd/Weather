package com.example.weather.screens.main

import androidx.lifecycle.*
import com.example.weather.data.cities.CitiesFields
import com.example.weather.data.weather.DailyWeatherMain
import kotlin.properties.Delegates

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    var permissionState = false

    val weatherThrowable = MutableLiveData<Throwable>()
    var lat by Delegates.notNull<Double>()
    var lon by Delegates.notNull<Double>()

    val weatherDailyLiveData by lazy {
        when(permissionState) {
            true -> interactor.subscribeOnWeatherDailyByLocation(::weatherDailyLoadedSuccess, ::loadedError)
            false -> interactor.subscribeOnWeatherDailyByCoord(lat, lon, ::weatherDailyLoadedSuccess, ::loadedError)
        }
        return@lazy MutableLiveData<DailyWeatherMain>()
    }

    val lastCityViewModel by lazy {
        interactor.getLastCity(::onComplete, ::loadedError)
        return@lazy MutableLiveData<CitiesFields>()
    }

    private fun onComplete(city: CitiesFields) {
        lastCityViewModel.postValue(city)
    }

    private fun weatherDailyLoadedSuccess(weatherDaily: DailyWeatherMain) {
        weatherDailyLiveData.postValue(weatherDaily)
    }

    private fun loadedError(throwable: Throwable) {
        weatherThrowable.postValue(throwable)
    }

    override fun onCleared() {
        interactor.clear()
    }

}