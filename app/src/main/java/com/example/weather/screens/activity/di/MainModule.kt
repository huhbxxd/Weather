package com.example.weather.screens.activity.di

import com.example.weather.data.CitiesApi
import com.example.weather.data.WeatherApi
import com.example.weather.data.repositories.cities.CitiesRepository
import com.example.weather.data.repositories.cities.CitiesRepositoryImpl
import com.example.weather.data.repositories.weather.WeatherRepository
import com.example.weather.data.repositories.weather.WeatherRepositoryImpl
import com.example.weather.domain.cities.CitiesInteractor
import com.example.weather.domain.weather.WeatherInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object MainModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(api)

    @Singleton
    @Provides
    @JvmStatic
    fun provideCitiesRepository(api: CitiesApi): CitiesRepository =
        CitiesRepositoryImpl(api)

    @Singleton
    @Provides
    @JvmStatic
    fun provideWeatherInteractor(repository: WeatherRepository): WeatherInteractor =
        WeatherInteractor(repository)

    @Singleton
    @Provides
    @JvmStatic
    fun provideCitiesInteractor(repository: CitiesRepository): CitiesInteractor =
        CitiesInteractor(repository)
}