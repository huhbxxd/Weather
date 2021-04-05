package com.example.weather.screens.list_cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ListCitiesViewModelFactory(val interactor: ListCitiesInteractor): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass == ListCitiesActivity::class.java) {
            return ListCitiesViewModel(interactor) as T
        }
        throw IllegalArgumentException()
    }


}