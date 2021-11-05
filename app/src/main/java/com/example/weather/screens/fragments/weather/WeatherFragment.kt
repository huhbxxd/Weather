package com.example.weather.screens.fragments.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather.core.base.BaseVMFragment
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.screens.fragments.weather.model.WeatherViewModel
import com.example.weather.utils.log

class WeatherFragment : BaseVMFragment<FragmentWeatherBinding, WeatherViewModel>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentWeatherBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.log("KudView")
    }
}