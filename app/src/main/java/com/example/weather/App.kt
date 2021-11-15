package com.example.weather

import com.example.weather.core.network.di.ApiModule
import com.example.weather.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .factory()
            .create(this, ApiModule())
    }
}