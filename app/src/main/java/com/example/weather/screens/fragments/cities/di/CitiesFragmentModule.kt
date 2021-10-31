package com.example.weather.screens.fragments.cities.di

import com.example.weather.di.app.FragmentScope
import com.example.weather.screens.fragments.cities.CitiesActivity
import com.example.weather.screens.fragments.cities.CitiesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class CitiesFragmentModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun provideCitiesFragment(): CitiesFragment

}