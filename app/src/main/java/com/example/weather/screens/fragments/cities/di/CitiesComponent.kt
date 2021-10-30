package com.example.weather.screens.fragments.cities.di

import com.example.weather.di.app.ActivityScope
import com.example.weather.di.app.AppComponent
import dagger.Component

@Component(modules = [CitiesModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface CitiesComponent {

//    fun inject(activity: CitiesActivity)

}