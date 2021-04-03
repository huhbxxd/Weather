package com.example.weather.data.repositories.weather

import android.location.Location
import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Single

interface WeatherRepository {

    fun loadWeatherDailyByCoord(lat: Double, lon: Double): Single<DailyWeatherMain>

    fun loadWeatherDailyByLocation(location: Location): Single<DailyWeatherMain>

}