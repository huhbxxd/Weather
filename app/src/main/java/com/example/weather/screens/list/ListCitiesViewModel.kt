package com.example.weather.screens.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.cities.CitiesFields

class ListCitiesViewModel(private val interactor: ListCitiesInteractor): ViewModel() {

    val listCitiesLiveData by lazy {
        interactor.getListCities(::onSuccess, ::onError)
        return@lazy MutableLiveData<MutableList<CitiesFields>>()
    }

    private fun onSuccess(list: MutableList<CitiesFields>) {
        listCitiesLiveData.postValue(list)
    }

    private fun onError(throwable: Throwable) {
        // some events
    }
}