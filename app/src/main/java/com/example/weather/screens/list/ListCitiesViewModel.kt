package com.example.weather.screens.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.cities.CitiesFields

class ListCitiesViewModel(private val interactor: ListCitiesInteractor): ViewModel() {

    val listCitiesLiveData by lazy {
        interactor.getListCities(::onSuccess, ::onError)
        return@lazy MutableLiveData<List<CitiesFields>>()
    }

    private fun onSuccess(list: List<CitiesFields>) {

    }

    private fun onError(throwable: Throwable) {

    }
}