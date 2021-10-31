package com.example.weather.screens.fragments.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weather.core.base.BaseFragment
import com.example.weather.databinding.FragmentCitiesBinding

class CitiesFragment: BaseFragment<FragmentCitiesBinding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentCitiesBinding.inflate(inflater, container, attachToParent)



}