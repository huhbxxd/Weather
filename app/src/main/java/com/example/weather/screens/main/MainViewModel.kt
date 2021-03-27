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

    val serviceLocation by lazy {
        interactor.getCoordinates(::locationSuccess, ::loadedError)
        return@lazy MutableLiveData<Location>()
    }

    val weatherDailyLiveData by lazy {
        interactor.subscribeOnWeatherDailyByCoord(55.22, 43.22, ::weatherDailyLoadedSuccess, ::loadedError)
        return@lazy MutableLiveData<DailyWeatherMain>()
    }

    val weatherThrowable = MutableLiveData<Throwable>()

    private fun weatherDailyLoadedSuccess(weatherDaily: DailyWeatherMain) {
        weatherDailyLiveData.postValue(weatherDaily)
    }

    private fun locationSuccess(location: Location) {
        serviceLocation.postValue(location)
    }

    private fun loadedError(throwable: Throwable) {
        weatherThrowable.postValue(throwable)
    }

    override fun onCleared() {
        interactor.clear()
    }

}