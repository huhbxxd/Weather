package com.example.weather.screens.main

import android.location.Location
import androidx.lifecycle.*
import com.example.weather.data.weather.daily.daily_day.DailyDayWeather
import com.example.weather.data.weather.DailyWeatherMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    val weatherThrowable = MutableLiveData<Throwable>()

    val weatherDailyLiveData by lazy {
        interactor.subscribeOnWeatherDailyByCoord(::weatherDailyLoadedSuccess, ::loadedError)
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