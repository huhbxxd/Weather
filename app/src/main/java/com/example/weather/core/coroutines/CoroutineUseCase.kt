package com.example.weather.core.coroutines

import com.example.weather.utils.log
import com.example.weather.utils.logErr
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class CoroutineUseCase<T, Params>: BaseCoroutineUseCase<T, Params>() {

    fun execute(
        coroutineScope: CoroutineScope = mainScope,
        onPreExecute: () -> Unit = {},
        onComplete: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onPostExecute: () -> Unit = {},
        params: Params? = null
    ) {
        if (job != null) {
            cancel()
        }

        coroutineScope.launch {
            onPreExecute()

            val deferred = doWorkAsync(params)

            try {
                onComplete.invoke(deferred.await())
            } catch (throwable: CancellationException) {
                log(this@CoroutineUseCase::class.simpleName, "The coroutine had canceled")
            } catch (throwable: Throwable) {
                logErr(throwable = throwable)
                onError(throwable)
                throwable.printStackTrace()
            }

            onPostExecute()
        }
    }
}