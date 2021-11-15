package com.example.weather.screens.fragments.start.di

import com.example.weather.di.app.FragmentScope
import com.example.weather.screens.fragments.start.StartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class StartFragmentModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun provideStartFragment(): StartFragment

}