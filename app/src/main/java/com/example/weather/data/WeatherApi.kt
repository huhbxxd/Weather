package com.example.weather.data

import com.example.weather.data.weather.DailyWeatherMain
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

//    @GET("/data/2.5/weather")
//    fun getWeatherDailyByName(
//        @Query("q") q: String,
//        @Query("units") units: String,
//        @Query("appid") appid: String
//    ): Single<DailyWeatherMain>

    @GET("/data/2.5/onecall")
    fun getWeatherDailyByCoord(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Single<DailyWeatherMain>

}