package com.example.weather.screens.main

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.weather.daily.daily_day.DailyDayWeather
import com.example.weather.data.weather.DailyWeatherMain
import kotlin.properties.Delegates

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    var latitude by Delegates.notNull<Double>()
    var longitude by Delegates.notNull<Double>()

    val weatherDailyLiveData by lazy {
        interactor.subscribeOnWeatherDailyByCoord(latitude, longitude, ::weatherDailyLoadedSuccess, ::weatherLoadedError)
        return@lazy MutableLiveData<DailyWeatherMain>()
    }

    val serviceLocation by lazy {
        interactor.getCoordinates(::locationSuccess, ::locationError)
        return@lazy MutableLiveData<Location>()
    }


    val weatherThrowable = MutableLiveData<Throwable>()

    private fun weatherDailyLoadedSuccess(weatherDaily: DailyWeatherMain) {
        weatherDailyLiveData.postValue(weatherDaily)
    }

    private fun weatherLoadedError(throwable: Throwable) {
        weatherThrowable.postValue(throwable)
    }

    private fun locationSuccess(location: Location) {
        serviceLocation.postValue(location)
    }

    private fun locationError(throwable: Throwable) {
        weatherThrowable.postValue(throwable)
    }

    override fun onCleared() {
        interactor.clear()
    }

}