package com.example.weather.screens.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather.core.base.BaseFragment
import com.example.weather.databinding.FragmentWeatherBinding

class WeatherFragment:  BaseFragment<FragmentWeatherBinding>(){

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentWeatherBinding = FragmentWeatherBinding.inflate(inflater, container, attachToParent)

}