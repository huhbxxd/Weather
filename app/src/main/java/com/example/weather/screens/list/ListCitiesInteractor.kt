package com.example.weather.screens.list

import com.example.weather.core.base.BaseInteractor
import com.example.weather.data.cities.CitiesFields
import com.example.weather.data.repositories.list.ListCitiesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListCitiesInteractor(private val citiesRepository: ListCitiesRepository): BaseInteractor() {

    fun getListCities(onSuccess: (MutableList<CitiesFields>) -> Unit, onError: (Throwable) -> Unit) {
        disposable.add(citiesRepository.getListCities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError))
    }

}