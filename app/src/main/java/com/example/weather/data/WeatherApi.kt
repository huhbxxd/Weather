package com.example.weather.data

import com.example.weather.data.weather.daily_day.DailyWeatherMain
import com.example.weather.data.weather.today.TodayWeather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather")
    fun getWeatherTodayByName(
        @Query("q") q: String,
        @Query("appid") appid: String
    ): Single<TodayWeather>

    @GET("/data/2.5/weather")
    fun getWeatherTodayByCoord(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): Single<TodayWeather>

    @GET("/data/2.5/onecall")
    fun getWeatherDailyByCoord(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("appid") appid: String
    ): Single<DailyWeatherMain>

}