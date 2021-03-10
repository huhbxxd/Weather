package com.example.weather.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather")
    fun getWeatherByName(@Query("q") q: String,
          @Query("appid") appid: String): Single<List<Weather>>

    @GET("/data/2.5/weather")
    fun getWeatherByCoord(@Query("lat") lat: Double,
                          @Query("lon") lon: Double,
                          @Query("appid") appid: String): Single<List<Weather>>

}