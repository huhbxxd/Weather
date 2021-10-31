package com.example.weather.screens.fragments.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.weather.core.base.BaseFragment
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.screens.fragments.weather.model.WeatherViewModel

class WeatherFragment:  BaseFragment<FragmentWeatherBinding>(){

    private val viewModel: WeatherViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentWeatherBinding = FragmentWeatherBinding.inflate(inflater, container, attachToParent)

}