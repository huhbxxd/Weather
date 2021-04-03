package com.example.weather.screens.cities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.cities.Cities
import com.example.weather.data.cities.CitiesRecord

class CitiesViewModel(private val interactor: CitiesInteractor): ViewModel() {

    private var page = 1

    private val citiesList = mutableListOf<CitiesRecord>()

    val citiesViewModel by lazy {
        interactor.subscribeOnCitiesSearch(::onSuccessCities, ::onError)
        return@lazy MutableLiveData<Pair<List<CitiesRecord>, Boolean>>()
    }

    private fun onSuccessCities(cities: Cities) {
        val hasLoading = cities.citiesRecords!!.size >= CitiesInteractor.PAGE_COUNT
        citiesList.addAll(cities.citiesRecords)
        citiesViewModel.postValue(Pair(citiesList, hasLoading))
    }

    fun onSearch(query: String) {
        page = 1
        citiesList.clear()
        interactor.postQuery(query, page, {}, ::onError)
    }

    fun onNextPage(query: String) {
        page++
        interactor.postQuery(query, page, {}, ::onError)
    }

    private fun onError(t: Throwable) {
        // some events
    }

    override fun onCleared() {
        interactor.clear()
    }
}