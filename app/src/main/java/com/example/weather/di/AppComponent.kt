package com.example.weather.di

import android.content.Context
import com.example.weather.App
import com.example.weather.data.CitiesApi
import com.example.weather.data.WeatherApi
import com.example.weather.data.repositories.stored.StoredCitiesRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(application: App)

    fun provideListCitiesRepository(): StoredCitiesRepository

    fun provideWeatherApi(): WeatherApi

    fun provideCitiesApi(): CitiesApi

    fun provideApplicationContext(): Context

}