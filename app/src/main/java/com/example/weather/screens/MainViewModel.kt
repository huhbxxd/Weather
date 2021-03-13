package com.example.weather.screens

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.WeatherData
import com.example.weather.data.repository.WeatherRepository

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    private companion object {
        private val NULL_POSITION = 0.0
    }

    private lateinit var geoPosition: Pair<Double, Double>

    val weatherLiveData by lazy {
        interactor.subscribeOnWeatherByCoord(geoPosition.first, geoPosition.second, ::weatherLoadedSuccess, ::weatherLoadedError)
        return@lazy MutableLiveData<List<WeatherData>>()
    }

    fun loadGeoPosition() {
        interactor.getCoordinates(::locationSuccess, ::locationError)
    }

    private fun weatherLoadedSuccess(data: List<WeatherData>) {
        weatherLiveData.postValue(data)
    }

    private fun weatherLoadedError(throwable: Throwable) {
//        event to error
    }

    private fun locationSuccess(location: Location) {
        geoPosition = location.latitude to location.longitude
    }

    private fun locationError(throwable: Throwable) {
        geoPosition = NULL_POSITION to NULL_POSITION // change event to handle error
    }

    override fun onCleared() {
        interactor.clear()
    }

}