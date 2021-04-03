package com.example.weather.screens.main.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.WeatherApi
import com.example.weather.data.repositories.location.LocationRepository
import com.example.weather.data.repositories.location.LocationRepositoryImpl
import com.example.weather.data.repositories.weather.WeatherRepository
import com.example.weather.data.repositories.weather.WeatherRepositoryImpl
import com.example.weather.di.ActivityScope
import com.example.weather.screens.main.MainActivity
import com.example.weather.screens.main.MainInteractor
import com.example.weather.screens.main.MainViewModel
import com.example.weather.screens.main.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: MainActivity) {

    @Provides
    @ActivityScope
    fun provideMainViewModel(interactor: MainInteractor) =
        ViewModelProvider(activity, MainViewModelFactory(interactor)).get(MainViewModel::class.java)

    @Provides
    @ActivityScope
    fun provideInteractor(repositoryWeather: WeatherRepository,
                          repositoryLocation: LocationRepository
    ) =
        MainInteractor(repositoryWeather, repositoryLocation)

    @Provides
    @ActivityScope
    fun provideCoordRepository(applicationContext: Context): LocationRepository =
        LocationRepositoryImpl(
            applicationContext
        )

    @Provides
    @ActivityScope
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(api)

}