package com.example.weather.screens.fragments.weather.di

import com.example.weather.screens.fragments.weather.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class WeatherFragmentModule {
    @ContributesAndroidInjector
    abstract fun provideWeatherFragment(): WeatherFragment
}