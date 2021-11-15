package com.example.weather.screens.fragments.cities.di

import androidx.lifecycle.ViewModel
import com.example.weather.di.app.FragmentScope
import com.example.weather.di.vm.ViewModelKey
import com.example.weather.screens.fragments.cities.CitiesFragment
import com.example.weather.screens.fragments.cities.model.CitiesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class CitiesFragmentModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun provideCitiesFragment(): CitiesFragment

    @Binds
    @IntoMap
    @ViewModelKey(CitiesViewModel::class)
    @FragmentScope
    internal abstract fun bindCitiesViewModel(viewModel: CitiesViewModel): ViewModel

}