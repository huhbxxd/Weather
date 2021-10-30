package com.example.weather.di

import android.content.Context
import com.example.weather.core.network.CitiesNetworkWeatherServiceProvider
import com.example.weather.core.network.CitiesServiceProvider
import com.example.weather.core.network.WeatherNetworkWeatherServiceProvider
import com.example.weather.core.network.WeatherServiceProvider
import com.example.weather.data.CitiesApi
import com.example.weather.data.WeatherApi
import com.example.weather.data.repositories.location.LocationRepository
import com.example.weather.data.repositories.location.LocationRepositoryImpl
import com.example.weather.data.repositories.stored.StoredCitiesRepository
import com.example.weather.data.repositories.stored.StoredCitiesRepositoryImpl
import com.example.weather.data.repositories.weather.WeatherRepository
import com.example.weather.data.repositories.weather.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    fun providerListCitiesRepository(applicationContext: Context): StoredCitiesRepository =
        StoredCitiesRepositoryImpl(applicationContext)

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideCoordRepository(applicationContext: Context): LocationRepository =
        LocationRepositoryImpl(applicationContext)

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