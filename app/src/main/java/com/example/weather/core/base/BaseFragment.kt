package com.example.weather.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import java.lang.NullPointerException

abstract class BaseFragment<VB: ViewBinding>(): DaggerFragment() {

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