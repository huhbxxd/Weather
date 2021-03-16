package com.example.weather.di

import android.app.Application
import android.content.Context
import com.example.weather.App
import com.example.weather.data.WeatherApi
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(application: App)

    fun provideWeatherApi(): WeatherApi

    fun provideApplicationContext(): Context
}