package com.example.weather.screens.cities.di

import com.example.weather.di.ActivityScope
import com.example.weather.di.AppComponent
import com.example.weather.screens.cities.CitiesActivity
import dagger.Component


@Component(modules = [CitiesModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface CitiesComponent {

    fun inject(activity: CitiesActivity)

}