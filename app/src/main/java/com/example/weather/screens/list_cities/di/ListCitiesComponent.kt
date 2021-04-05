package com.example.weather.screens.list_cities.di

import com.example.weather.di.ActivityScope
import com.example.weather.di.AppComponent
import com.example.weather.screens.list_cities.ListCitiesActivity
import dagger.Component

@Component(modules = [ListCitiesModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface ListCitiesComponent {

    fun inject(activity: ListCitiesActivity)

}