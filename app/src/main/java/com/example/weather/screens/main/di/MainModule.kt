package com.example.weather.screens.main.di

import android.app.Application
import android.content.Context
import com.example.weather.data.WeatherApi
import com.example.weather.data.repository.CoordRepository
import com.example.weather.data.repository.CoordRepositoryImpl
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.di.ActivityScope
import com.example.weather.screens.main.MainInteractor
import com.example.weather.screens.main.MainViewModel
import com.example.weather.utils.Workers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun provideMainViewModel(interactor: MainInteractor) = MainViewModel(interactor)

    @Provides
    @ActivityScope
    fun provideInteractor(workers: Workers, repositoryWeather: WeatherRepository, repositoryCoord: CoordRepository)
            = MainInteractor(workers, repositoryWeather, repositoryCoord)

    @Provides
    @ActivityScope
    fun provideCoordRepository(applicationContext: Context): CoordRepository
            = CoordRepositoryImpl(applicationContext)

    @Provides
    @ActivityScope
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository
            = WeatherRepositoryImpl(api)

}