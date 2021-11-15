package com.example.weather.di.app

import android.content.Context
import com.example.weather.App
import com.example.weather.data.repositories.stored.StoredCitiesRepository
import com.example.weather.data.repositories.stored.StoredCitiesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun provideApplicationContext(application: App): Context

    companion object {
        @Provides
        @Singleton
        fun providerListCitiesRepository(applicationContext: Context): StoredCitiesRepository =
            StoredCitiesRepositoryImpl(applicationContext)

    }

}