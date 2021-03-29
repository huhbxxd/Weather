package com.example.weather.core.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseInteractor {

    protected val disposable = CompositeDisposable()

    open fun clear() {
        disposable.clear()
    }

}