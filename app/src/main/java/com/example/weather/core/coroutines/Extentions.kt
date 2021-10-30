package com.example.weather.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

suspend fun <B> CoroutineScope.backgroundAsync(
    context: CoroutineContext = computationContext,
    block: suspend () -> B
): Deferred<B> {
    return async(context) {
        block.invoke()
    }
}