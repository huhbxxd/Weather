package com.example.weather.screens.main

import androidx.lifecycle.*
import com.example.weather.data.cities.CitiesFields
import com.example.weather.data.weather.DailyWeatherMain

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    val weatherThrowable = MutableLiveData<Throwable>()

    val weatherLiveDataLocation by lazy {
        interactor.subscribeOnWeatherDailyByLocation(::weatherLocationLoadedSuccess, ::loadedError)
        return@lazy MutableLiveData<DailyWeatherMain>()
    }

    val weatherLiveDataByCity by lazy {
        interactor.subscribeOnWeatherDailyByCoord(::weatherCityLoadedSuccess, ::loadedError)
        return@lazy MutableLiveData<DailyWeatherMain>()
    }

    val cityNameLiveData by lazy {
        interactor.getLastCityName(::lastCityLoaded, ::loadedError)
        return@lazy MutableLiveData<String>()
    }

    private fun weatherLocationLoadedSuccess(weatherDaily: DailyWeatherMain) {
        weatherLiveDataLocation.postValue(weatherDaily)
    }

    private fun weatherCityLoadedSuccess(weatherDaily: DailyWeatherMain) {
        weatherLiveDataByCity.postValue(weatherDaily)
    }

    private fun lastCityLoaded(city: CitiesFields) {
        cityNameLiveData.postValue(city.cityName)
    }


    private fun loadedError(throwable: Throwable) {
        weatherThrowable.postValue(throwable)
    }

    override fun onCleared() {
        interactor.clear()
    }

}