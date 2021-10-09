package com.example.weather.data

import com.example.weather.data.response.weather.DailyWeatherMain
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/onecall")
    suspend fun getWeatherDaily(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): DailyWeatherMain

}