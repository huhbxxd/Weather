package com.example.weather.screens.list.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.repositories.list.ListCitiesRepository
import com.example.weather.data.repositories.list.ListCitiesRepositoryImpl
import com.example.weather.di.ActivityScope
import com.example.weather.screens.list.ListCitiesActivity
import com.example.weather.screens.list.ListCitiesInteractor
import com.example.weather.screens.list.ListCitiesViewModel
import com.example.weather.screens.list.ListCitiesViewModelFactory
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
    fun provideListCitiesInteractor(repository: ListCitiesRepository) =
        ListCitiesInteractor(repository)

    @Provides
    @ActivityScope
    fun provideListCitiesRepository(applicationContext: Context): ListCitiesRepository =
        ListCitiesRepositoryImpl(applicationContext)

}