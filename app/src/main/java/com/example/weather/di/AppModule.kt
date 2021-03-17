package com.example.weather.di

import android.content.Context
import com.example.weather.core.network.NetworkServiceProvider
import com.example.weather.core.network.ServiceProvider
import com.example.weather.data.WeatherApi
import com.example.weather.utils.Workers
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    fun provideWeatherApi(serviceProvider: ServiceProvider) = serviceProvider.createService(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideWokers(): Workers = Workers(AndroidSchedulers.mainThread(), Schedulers.io())

    @Provides
    @Singleton
    fun provideServiceProvider(): ServiceProvider = NetworkServiceProvider()

    @Provides
    @Singleton
    fun provideApplicationContext() = applicationContext

}