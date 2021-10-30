package com.example.weather.core.coroutines

import com.example.weather.domain.applicationContext
import com.example.weather.domain.backgroundAsync
import kotlinx.coroutines.*

/**
 * merseyside kmp-support library
 */

abstract class BaseCoroutineUseCase<T, Params> {

    protected val mainScope: CoroutineScope by lazy { CoroutineScope(applicationContext) }

    private val asyncJob = SupervisorJob()
    private val scope = CoroutineScope(asyncJob)

    var job: Job? = null
        set(value) {
            field?.let {
                if (isActive) {
                    it.cancel()
                }
            }

            field = value
        }

    val isActive: Boolean
        get() { return job?.isActive ?: false}

    protected abstract suspend fun executeOnBackground(params: Params?): T

    protected suspend fun doWorkAsync(params: Params?): Deferred<T> = scope.backgroundAsync {
        executeOnBackground(params)
    }.also { job = it }

    fun cancel(cause: CancellationException? = null) {
        job?.cancel(cause)
        job = null
    }
}