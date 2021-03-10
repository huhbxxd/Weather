package com.example.weather.core.network

interface ServiceProvider {

    fun <T>createService(service: Class<T>): T

}