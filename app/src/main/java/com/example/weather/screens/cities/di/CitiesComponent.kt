package com.example.weather.screens.cities.di

import com.example.weather.di.AppComponent
import dagger.Component


@Component(modules = [CitiesModule::class], dependencies = [AppComponent::class])
interface CitiesComponent {


}