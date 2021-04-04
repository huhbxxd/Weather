package com.example.weather.di

import android.content.Context
import com.example.weather.core.network.CitiesNetworkWeatherServiceProvider
import com.example.weather.core.network.CitiesServiceProvider
import com.example.weather.core.network.WeatherNetworkWeatherServiceProvider
import com.example.weather.core.network.WeatherServiceProvider
import com.example.weather.data.CitiesApi
import com.example.weather.data.WeatherApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    fun provideWeatherApi(weatherServiceProvider: WeatherServiceProvider) =
        weatherServiceProvider.createService(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideCitiesApi(weatherServiceProvider: CitiesServiceProvider) =
        weatherServiceProvider.createService(CitiesApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherServiceProvider(): WeatherServiceProvider =  WeatherNetworkWeatherServiceProvider()

    @Provides
    @Singleton
    fun provideCitiesServiceProvider(): CitiesServiceProvider = CitiesNetworkWeatherServiceProvider()

    @Provides
    @Singleton
    fun provideApplicationContext() =  applicationContext

}