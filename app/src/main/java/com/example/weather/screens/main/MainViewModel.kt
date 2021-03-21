package com.example.weather.screens.main

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.weather.daily_day.DailyWeather
import com.example.weather.data.weather.daily_day.DailyWeatherMain

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    private companion object {
        private val NULL_POSITION = 0.0
    }

    private lateinit var geoPosition: Pair<Double, Double>

//    val weatherTodayLiveData by lazy {
//        interactor.subscribeOnWeatherTodayByCoord(53.54628, 49.35037, ::weatherTodayLoadedSuccess, ::weatherLoadedError)
//        return@lazy MutableLiveData<TodayWeather>()
//    }

    val weatherDailyLiveData by lazy {
        interactor.subscribeOnWeatherDailyByCoord(53.54628, 49.35037, ::weatherDailyLoadedSuccess, ::weatherLoadedError)
        return@lazy MutableLiveData<List<DailyWeather>>()
    }

    private fun weatherDailyLoadedSuccess(weatherDaily: DailyWeatherMain) {
        weatherDailyLiveData.postValue(weatherDaily.daily)
    }

//    private fun weatherTodayLoadedSuccess(weather: TodayWeather) {
//        weatherTodayLiveData.postValue(weather)
//    }

    private fun weatherLoadedError(throwable: Throwable) {

    }

    fun loadGeoPosition() {
        interactor.getCoordinates(::locationSuccess, ::locationError)
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