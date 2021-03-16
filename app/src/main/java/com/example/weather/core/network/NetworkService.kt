package com.example.weather.core.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceProvider: ServiceProvider {

    private val BASE_URL = "http://api.openweathermap.org"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }

}