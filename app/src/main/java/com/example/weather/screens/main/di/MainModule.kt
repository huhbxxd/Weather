package com.example.weather.screens.main.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.WeatherApi
import com.example.weather.data.repositories.CoordRepository
import com.example.weather.data.repositories.CoordRepositoryImpl
import com.example.weather.data.repositories.WeatherRepository
import com.example.weather.data.repositories.WeatherRepositoryImpl
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
                          repositoryCoord: CoordRepository) =
        MainInteractor(repositoryWeather, repositoryCoord)

    @Provides
    @ActivityScope
    fun provideCoordRepository(applicationContext: Context): CoordRepository =
        CoordRepositoryImpl(applicationContext)

    @Provides
    @ActivityScope
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(api)

}