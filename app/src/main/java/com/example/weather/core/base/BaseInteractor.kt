package com.example.weather.core.base

import com.example.weather.utils.Workers
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseInteractor(private val workers: Workers) {

    protected val disposable = CompositeDisposable()

    open fun clear() {
        disposable.clear()
    }

    fun <T>Single<T>.schedule() = apply {
        observeOn(workers.observe)
        subscribeOn(workers.observe)
    }

    fun <T>Observable<T>.schedule() = apply {
        observeOn(workers.observe)
        subscribeOn(workers.subscribe)
    }

}