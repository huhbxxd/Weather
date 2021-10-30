package com.example.weather.utils

import android.util.Log

inline fun <reified U>U.log(tag: String? = U::class.simpleName, message: String? = ""): U {
    Log.d(tag, message ?: U::class.java.name)
    return this
}

inline fun <reified  T>T.logErr(tag: String? = T::class.simpleName, throwable: Throwable?): T {
    Log.e(tag, throwable?.message ?: T::class.java.name)
    return this
}