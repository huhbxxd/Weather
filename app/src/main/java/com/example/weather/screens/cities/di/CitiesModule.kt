package com.example.weather.screens.cities.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.CitiesApi
import com.example.weather.data.repositories.cities.CitiesRepository
import com.example.weather.data.repositories.cities.CitiesRepositoryImpl
import com.example.weather.data.repositories.stored.StoredCitiesRepository
import com.example.weather.data.repositories.stored.StoredCitiesRepositoryImpl
import com.example.weather.di.ActivityScope
import com.example.weather.screens.cities.CitiesActivity
import com.example.weather.screens.cities.CitiesInteractor
import com.example.weather.screens.cities.CitiesViewModel
import com.example.weather.screens.cities.CitiesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CitiesModule(private val activity: CitiesActivity) {

    @Provides
    @ActivityScope
    fun provideViewModel(interactor: CitiesInteractor) =
        ViewModelProvider(activity, CitiesViewModelFactory(interactor)).get(CitiesViewModel::class.java)

    @Provides
    @ActivityScope
    fun provideCitiesInteractor(repository: CitiesRepository,
                                repositoryStored: StoredCitiesRepository) =
        CitiesInteractor(repository, repositoryStored)

    @Provides
    @ActivityScope
    fun provideCitiesRepository(api: CitiesApi): CitiesRepository =
        CitiesRepositoryImpl(api)

}