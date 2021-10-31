package com.example.weather.utils

import android.util.Log
import com.example.weather.core.coroutines.BaseCoroutineUseCase
import java.lang.IllegalStateException
import java.lang.NullPointerException

inline fun <reified U>U.log(tag: String? = U::class.simpleName, message: String? = ""): U {
    Log.d(tag, message ?: U::class.java.name)
    return this
}

inline fun <reified  T>T.logErr(tag: String? = T::class.simpleName, throwable: Throwable?): T {
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