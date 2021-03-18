package com.example.weather.di

import android.content.Context
import com.example.weather.core.network.NetworkServiceProvider
import com.example.weather.core.network.ServiceProvider
import com.example.weather.data.WeatherApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    fun provideWeatherApi(serviceProvider: ServiceProvider) =
        serviceProvider.createService(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideServiceProvider(): ServiceProvider =  NetworkServiceProvider()

    @Provides
    @Singleton
    fun provideApplicationContext() =  applicationContext

}