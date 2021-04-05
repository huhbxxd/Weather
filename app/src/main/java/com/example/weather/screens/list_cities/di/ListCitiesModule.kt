package com.example.weather.screens.list_cities.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.repositories.list_cities.ListCitiesRepository
import com.example.weather.data.repositories.list_cities.ListCitiesRepositoryImpl
import com.example.weather.di.ActivityScope
import com.example.weather.screens.list_cities.ListCitiesActivity
import com.example.weather.screens.list_cities.ListCitiesInteractor
import com.example.weather.screens.list_cities.ListCitiesViewModel
import com.example.weather.screens.list_cities.ListCitiesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ListCitiesModule(private val activity: ListCitiesActivity) {


    @Provides
    @ActivityScope
    fun provideViewModel(interactor: ListCitiesInteractor) =
        ViewModelProvider(activity, ListCitiesViewModelFactory(interactor)).get(ListCitiesViewModel::class.java)

    @Provides
    @ActivityScope
    fun provideInteractor(repository: ListCitiesRepository) = ListCitiesInteractor(repository)

    @Provides
    @ActivityScope
    fun provideListCitiesRepository(applicationContext: Context) =
        ListCitiesRepositoryImpl(applicationContext)

}