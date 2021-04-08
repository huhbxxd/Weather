package com.example.weather.screens.main

import androidx.lifecycle.*
import com.example.weather.data.cities.CitiesFields
import com.example.weather.data.weather.DailyWeatherMain
import kotlin.properties.Delegates

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    var permissionState = false

    val weatherThrowable = MutableLiveData<Throwable>()

    val weatherDailyLiveData by lazy {
        when(permissionState) {
            true -> interactor.subscribeOnWeatherDailyByLocation(::weatherDailyLoadedSuccess, ::loadedError)
            false -> interactor.subscribeOnWeatherDailyByCoord(::weatherDailyLoadedSuccess, ::loadedError)
        }
        return@lazy MutableLiveData<DailyWeatherMain>()
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