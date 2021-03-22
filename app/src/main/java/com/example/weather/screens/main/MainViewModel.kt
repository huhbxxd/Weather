package com.example.weather.screens.main

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.weather.daily.daily_day.DailyDayWeather
import com.example.weather.data.weather.DailyWeatherMain

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    private companion object {
        private val NULL_POSITION = 0.0
    }

    private lateinit var geoPosition: Pair<Double, Double>

    val weatherDailyLiveData by lazy {
        interactor.subscribeOnWeatherDailyByCoord(53.54628, 49.35037, ::weatherDailyLoadedSuccess, ::weatherLoadedError)
        return@lazy MutableLiveData<DailyWeatherMain>()
    }

    val weatherThrowable = MutableLiveData<Throwable>()

    private fun weatherDailyLoadedSuccess(weatherDaily: DailyWeatherMain) {
        weatherDailyLiveData.postValue(weatherDaily)
    }

    private fun weatherLoadedError(throwable: Throwable) {
        weatherThrowable.postValue(throwable)
    }

    fun loadGeoPosition() {
        interactor.getCoordinates(::locationSuccess, ::locationError)
    }

    private fun locationSuccess(location: Location) {
        geoPosition = Pair(location.latitude, location.longitude)
    }

    private fun locationError(throwable: Throwable) {
        weatherThrowable.postValue(throwable)
    }

    override fun onCleared() {
        interactor.clear()
    }

}