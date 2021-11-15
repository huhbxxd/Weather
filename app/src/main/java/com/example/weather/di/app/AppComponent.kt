package com.example.weather.di.app

import com.example.weather.App
import com.example.weather.core.network.di.ApiModule
import com.example.weather.screens.activity.di.MainActivityBuilder
import com.example.weather.screens.activity.di.MainModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityBuilder::class,
        ApiModule::class,
        MainModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: App,
            @BindsInstance apiModule: ApiModule
        ): AppComponent
    }

}