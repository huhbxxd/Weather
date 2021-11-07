package com.example.weather.core.network

sealed class NetworkResponse<out T: Any, out U: Any> {
    data class Success<T: Any>(val body: T): NetworkResponse<T, Nothing>()
    data class Error<U: Any>(val body: U): NetworkResponse<Nothing, U>()
}