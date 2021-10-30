package com.example.weather.screens.activity.di

import com.example.weather.di.ActivityScope
import com.example.weather.di.AppComponent
import com.example.weather.screens.main.di.MainModule
import dagger.Component

@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface ActivityComponent {


}