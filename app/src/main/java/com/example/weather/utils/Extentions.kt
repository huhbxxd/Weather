package com.example.weather.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weather.core.coroutines.BaseCoroutineUseCase
import java.lang.IllegalStateException
import java.lang.NullPointerException

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

inline fun <reified VM: ViewModel> getViewModel(): VM {
    return VM::class.java.newInstance()
}