package com.example.weather.screens.fragments.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.weather.core.base.BaseVMFragment
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.di.vm.ViewModelFactory
import com.example.weather.screens.fragments.weather.model.WeatherViewModel
import com.example.weather.utils.injectViewModel
import com.example.weather.utils.log
import javax.inject.Inject

class WeatherFragment : BaseVMFragment<FragmentWeatherBinding, WeatherViewModel>() {

    override val makeViewModel: Class<WeatherViewModel>
        get() = WeatherViewModel::class.java

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentWeatherBinding.inflate(inflater, container, attachToParent)




}