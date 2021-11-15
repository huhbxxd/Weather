package com.example.weather.screens.main.di

import com.example.weather.di.app.ActivityScope
import com.example.weather.di.app.AppComponent
import dagger.Component

@Component(modules = [ActiivMod::class], dependencies = [AppComponent::class])
@ActivityScope
interface MainComponent {

//    fun inject(activityOld: MainActivityOld)

}