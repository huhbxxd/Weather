package com.example.weather.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.repository.WeatherRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(val interactor: MainInteractor): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java)
            return MainViewModel(interactor) as T
        throw IllegalArgumentException()
    }

}