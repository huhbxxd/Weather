package com.example.weather.screens.fragments.weather.di

import androidx.lifecycle.ViewModel
import com.example.weather.di.app.FragmentScope
import com.example.weather.di.vm.ViewModelKey
import com.example.weather.screens.fragments.weather.WeatherFragment
import com.example.weather.screens.fragments.weather.model.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class WeatherFragmentModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun provideWeatherFragment(): WeatherFragment

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    @FragmentScope
    internal abstract fun bindWeatherViewModel(viewModel: WeatherViewModel): ViewModel

}