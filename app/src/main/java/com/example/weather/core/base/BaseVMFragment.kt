package com.example.weather.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.weather.di.vm.ViewModelFactory
import com.example.weather.utils.getViewModel
import com.example.weather.utils.log
import dagger.android.support.DaggerFragment
import java.lang.NullPointerException
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseVMFragment<VB: ViewBinding, VM: ViewModel>: BaseFragment<VB>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel::class.java
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
