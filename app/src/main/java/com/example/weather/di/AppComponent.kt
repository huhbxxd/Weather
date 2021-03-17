package com.example.weather.di

import android.app.Application
import android.content.Context
import com.example.weather.App
import com.example.weather.data.WeatherApi
import com.example.weather.utils.Workers
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(application: App)

    fun provideWeatherApi(): WeatherApi

    fun provideApplicationContext(): Context

    fun provideWorkers(): Workers
}