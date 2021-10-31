package com.example.weather.screens.fragments.cities.model

import androidx.lifecycle.*
import com.example.weather.data.response.cities.Cities
import com.example.weather.domain.cities.CitiesInteractor
import javax.inject.Inject

class CitiesViewModel @Inject constructor(
    private val citiesUseCase: CitiesInteractor
) : ViewModel() {

    private val mutCitiesLiveData = MutableLiveData<Cities>()
    val citiesLiveData: LiveData<Cities> = mutCitiesLiveData

    private val mutQueryCityLiveData = MutableLiveData(EMPTY_STRING)

    fun getCities() {

    }

    init {

    }

    fun setQuery(value: String) {
        mutQueryCityLiveData.value = value
    }



    companion object {
        private const val PAGE_COUNT = 30
        private const val EMPTY_STRING = ""
    }
}