package com.example.weather.screens.main

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.weather.Today.TodayWeather

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    private companion object {
        private val NULL_POSITION = 0.0
    }

    private lateinit var geoPosition: Pair<Double, Double>

    val weatherTodayLiveData by lazy {
        interactor.subscribeOnWeatherTodayByCoord(53.54628, 49.35037, ::weatherLoadedSuccess, ::weatherLoadedError)
        return@lazy MutableLiveData<TodayWeather>()
    }

    fun loadGeoPosition() {
        interactor.getCoordinates(::locationSuccess, ::locationError)
    }

    private fun weatherLoadedSuccess(weather: TodayWeather) {
        weatherTodayLiveData.postValue(weather)
    }

    private fun weatherLoadedError(throwable: Throwable) {

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