package com.example.weather.screens.main.di

import com.example.weather.di.ActivityScope
import com.example.weather.di.AppComponent
import com.example.weather.screens.main.MainActivity
import dagger.Component

@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface MainComponent {

    fun inject(activity: MainActivity)

//    fun provideWorkers(): Workers

}