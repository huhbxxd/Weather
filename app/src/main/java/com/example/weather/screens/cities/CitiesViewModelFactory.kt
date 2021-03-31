package com.example.weather.screens.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CitiesViewModelFactory(val interactor: CitiesInteractor): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == CitiesViewModel::class.java)
            return CitiesViewModel(interactor) as T
        throw IllegalArgumentException()
    }

}