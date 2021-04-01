package com.example.weather.screens.cities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.cities.Cities

class CitiesViewModel(private val interactor: CitiesInteractor): ViewModel() {

    private var page = 1

    val citiesViewModel by lazy {
        interactor.subscribeOnCitiesSearch(::onSuccessCities, ::onError)
        return@lazy MutableLiveData<Cities>()
    }

    private fun onSuccessCities(cities: Cities) {
        citiesViewModel.postValue(cities)
    }

    fun onSearch(query: String) {
        page = 1
        interactor.postQuery(query, page, {}, ::onError)
    }

    fun onNextPage(query: String) {
        page++
        interactor.postQuery(query, page, {}, ::onError)
    }

    private fun onError(t: Throwable) {
        // some events
    }
}