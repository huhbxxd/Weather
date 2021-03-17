package com.example.weather.screens.main

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.WeatherData

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    private companion object {
        private val NULL_POSITION = 0.0
    }

    private val listWeatherData = mutableListOf<WeatherData>()

    private lateinit var geoPosition: Pair<Double, Double>

    val weatherLiveData by lazy {
        interactor.subscribeOnWeatherByCoord(53.54628, 49.35037, ::weatherLoadedSuccess, ::weatherLoadedError)
        return@lazy MutableLiveData<List<WeatherData>>()
    }

    fun loadGeoPosition() {
        interactor.getCoordinates(::locationSuccess, ::locationError)
    }

    private fun weatherLoadedSuccess(data: List<WeatherData>) {
        listWeatherData.addAll(data)
        weatherLiveData.postValue(listWeatherData)
    }

    private fun weatherLoadedError(throwable: Throwable) {
        listWeatherData.clear()

//        weatherLiveData.postValue(listWeatherData)
    }

    private fun locationSuccess(location: Location) {
        geoPosition = Pair(location.latitude, location.longitude)
    }

    private fun locationError(throwable: Throwable) {
        geoPosition = NULL_POSITION to NULL_POSITION // change event to handle error
    }

    override fun onCleared() {
        interactor.clear()
    }

}