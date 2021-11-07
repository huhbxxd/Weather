package com.example.weather.core.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.weather.di.vm.ViewModelFactory
import javax.inject.Inject

abstract class BaseVMFragment<VB : ViewBinding, VM : ViewModel> : BaseFragment<VB>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: VM

    abstract val makeViewModel: Class<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, viewModelFactory)[makeViewModel]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
