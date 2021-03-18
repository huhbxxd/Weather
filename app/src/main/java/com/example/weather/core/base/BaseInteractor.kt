package com.example.weather.core.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseInteractor {

    protected val disposable = CompositeDisposable()

    open fun clear() {
        disposable.clear()
    }

//    fun <T>Single<T>.schedule() = apply {
//        observeOn(workers.observe)
//        subscribeOn(workers.subscribe)
//    }
//
//    fun <T>Observable<T>.schedule() = apply {
//        observeOn(workers.observe)
//        subscribeOn(workers.subscribe)
//    }

}