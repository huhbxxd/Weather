package com.example.weather.screens.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ListCitiesViewModelFactory(val interactor: ListCitiesInteractor): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ListCitiesViewModel::class.java)
                return ListCitiesViewModel(interactor) as T
            throw IllegalArgumentException()
    }

}