package com.example.weather.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.weather.di.vm.ViewModelFactory
import com.example.weather.utils.getViewModel
import dagger.android.support.DaggerFragment
import java.lang.NullPointerException
import javax.inject.Inject

abstract class BaseFragment<VB: ViewBinding>: DaggerFragment() {

    protected var binding: VB? = null

    protected fun requireBinding(): VB {
        return binding ?: throw NullPointerException("Not inited binding")
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateViewBinding(inflater, container, false)
        return requireBinding().root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}