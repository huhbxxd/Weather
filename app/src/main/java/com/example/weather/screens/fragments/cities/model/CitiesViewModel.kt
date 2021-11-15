package com.example.weather.screens.fragments.cities.model

import androidx.lifecycle.*
import com.example.weather.data.response.cities.Cities
import com.example.weather.domain.cities.CitiesInteractor
import com.example.weather.utils.log
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            mutQueryCityLiveData.asFlow().collect {
                citiesUseCase.execute(
                    params = CitiesInteractor.Params(
                        query = it,
                        page = PAGE_COUNT
                    ),
                    onComplete = { cities ->
                        mutCitiesLiveData.postValue(cities)
                    }
                )
            }
        }
    }

    fun setQuery(value: String?) {
        mutQueryCityLiveData.value = value ?: EMPTY_STRING
    }



    companion object {
        private const val PAGE_COUNT = 30
        private const val EMPTY_STRING = ""
    }
}