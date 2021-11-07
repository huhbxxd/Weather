package com.example.weather.utils

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.core.coroutines.BaseCoroutineUseCase
import com.example.weather.di.vm.ViewModelFactory
import java.lang.IllegalStateException
import java.lang.NullPointerException
import kotlin.reflect.KClass

inline fun <reified U>U.log(message: String? = "", tag: String? = U::class.simpleName): U {
    Log.d(tag, message ?: U::class.toString())
    return this
}

inline fun <reified  T>T.logErr( throwable: Throwable?, tag: String? = T::class.simpleName): T {
    Log.e(tag, throwable?.message ?: T::class.java.name)
    return this
}

inline fun <Params, Result> BaseCoroutineUseCase<*, Params>.requireParams(
    params: Params?,
    block: Params.() -> Result
): Result {
    return if (params != null) {
        block(params)
    } else throw IllegalStateException("No params")
}

inline fun <reified VM: ViewModel> Fragment.injectViewModel(factory: ViewModelFactory): VM {
    return ViewModelProvider(this, factory)[VM::class.java]
}

inline fun <reified VM: ViewModel> getViewModelClass(): Class<VM> {
    return VM::class.java
}