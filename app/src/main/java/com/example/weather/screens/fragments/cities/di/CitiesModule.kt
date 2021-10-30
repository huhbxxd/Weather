package com.example.weather.screens.fragments.cities.di

import com.example.weather.screens.fragments.cities.CitiesActivity
import dagger.Module

@Module
class CitiesModule(private val activity: CitiesActivity) {

//    @Provides
//    @ActivityScope
//    fun provideViewModel(interactor: CitiesInteractor) =
//        ViewModelProvider(activity, CitiesViewModelFactory(interactor)).get(CitiesViewModel::class.java)
//
//    @Provides
//    @ActivityScope
//    fun provideCitiesInteractor(repository: CitiesRepository,
//                                repositoryStored: StoredCitiesRepository) =
//        CitiesInteractor(repository, repositoryStored)
//
//    @Provides
//    @ActivityScope
//    fun provideCitiesRepository(api: CitiesApi): CitiesRepository =
//        CitiesRepositoryImpl(api)

}