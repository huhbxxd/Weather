package com.example.weather.core.network

interface WeatherServiceProvider {

    fun <T>createService(service: Class<T>): T

}