package com.example.weather.screens.main.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.WeatherApi
import com.example.weather.data.repository.CoordRepository
import com.example.weather.data.repository.CoordRepositoryImpl
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.di.ActivityScope
import com.example.weather.screens.main.MainActivity
import com.example.weather.screens.main.MainInteractor
import com.example.weather.screens.main.MainViewModel
import com.example.weather.screens.main.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: MainActivity) {

    @Provides
    @ActivityScope
    fun provideMainViewModel(interactor: MainInteractor) =
        ViewModelProvider(activity, MainViewModelFactory(interactor)).get(MainViewModel::class.java)

    @Provides
    @ActivityScope
    fun provideInteractor(/* workers: Workers ,*/
                          repositoryWeather: WeatherRepository,
                          repositoryCoord: CoordRepository) =
        MainInteractor(repositoryWeather, repositoryCoord)

    @Provides
    @ActivityScope
    fun provideCoordRepository(applicationContext: Context): CoordRepository =
        CoordRepositoryImpl(applicationContext)

    @Provides
    @ActivityScope
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(api)

//    @Provides
//    @ActivityScope
//    fun provideWokers(): Workers =
//        Workers(AndroidSchedulers.mainThread(), Schedulers.io())

}