package com.example.weather.data.repositories

import android.location.Location
import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Single

interface WeatherRepository {

//    fun loadWeatherDailyByName(cityName: String): Single<DailyWeatherMain>

    fun loadWeatherDailyByCoord(location: Location): Single<DailyWeatherMain>

}