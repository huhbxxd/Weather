package com.example.weather.screens.activity.di

import com.example.weather.di.app.ActivityScope
import com.example.weather.screens.activity.MainActivity
import com.example.weather.screens.fragments.cities.di.CitiesFragmentModule
import com.example.weather.screens.fragments.start.di.StartFragmentModule
import com.example.weather.screens.fragments.weather.di.WeatherFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            WeatherFragmentModule::class,
            StartFragmentModule::class,
            CitiesFragmentModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity


}