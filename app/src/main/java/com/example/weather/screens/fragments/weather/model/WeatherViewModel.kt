package com.example.weather.screens.fragments.weather.model

import androidx.lifecycle.ViewModel
import com.example.weather.domain.weather.WeatherInteractor
import com.example.weather.utils.log
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val weatherUseCase: WeatherInteractor
) : ViewModel() {

    init {

    }

    fun cud() {
        log("KudKudKud")
    }
}