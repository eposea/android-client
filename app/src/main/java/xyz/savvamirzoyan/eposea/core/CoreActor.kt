package xyz.savvamirzoyan.eposea.core

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class CoreActor<T>(coroutineScope: CoroutineScope) {

    private val channel: Channel<CoreActorActions<T>> = Channel(Channel.Factory.UNLIMITED)

    init {
        coroutineScope.launch {
            var variable: T? = null

            for (action in channel) {
                when (action) {
                    is CoreActorActions.Get -> action.completableDeferred.complete(variable)
                    is CoreActorActions.Set -> {
                        variable = action.value
                    }
                }
            }
        }
    }

    suspend fun set(value: T) {
        channel.send(CoreActorActions.Set(value))
    }

    suspend fun get(): T? {
        val completable = CompletableDeferred<T?>()
        channel.send(CoreActorActions.Get(completable))
        return completable.await()
    }

    private sealed class CoreActorActions<T> {
        data class Set<T>(val value: T) : CoreActorActions<T>()
        data class Get<T>(val completableDeferred: CompletableDeferred<T?>) : CoreActorActions<T>()
    }
}