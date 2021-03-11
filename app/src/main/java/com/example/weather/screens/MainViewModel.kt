package com.example.weather.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.WeatherData
import com.example.weather.data.repository.WeatherRepository

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    val weatherList = mutableListOf<WeatherData>()

    val weatherLiveData by lazy {
        interactor.subscribe
        return@lazy MutableLiveData<>()
    }


    fun weatherLoadedSuccess(list: List<WeatherData>) {
        weatherList.addAll(list)
    }

    fun weatherLoadedError(throwable: Throwable) {
        weatherList.clear()
    }

    override fun onCleared() {
        interactor.clear()
    }

}