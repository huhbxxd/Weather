package com.example.weather.core.network

interface CitiesServiceProvider {

    fun <T>createService(service: Class<T>): T

}