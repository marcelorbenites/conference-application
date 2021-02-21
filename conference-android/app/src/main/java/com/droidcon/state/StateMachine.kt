package com.droidcon.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateMachine<T, E>(private val errorFactory: ErrorFactory<E>) {

    private val internalState: MutableStateFlow<State<T, E>> = MutableStateFlow(Idle)

    private fun currentValue() = when (val state = internalState.value) {
        is Idle -> null
        is Loading<T> -> state.value
        is Loaded<T> -> state.value
        is Failure<T, E> -> state.value
    }

    val state = internalState.asStateFlow()

    fun moveToLoading() {
        internalState.value = Loading(currentValue())
    }

    fun moveToLoaded(value: T) {
        internalState.value = Loaded(value)
    }

    fun moveToError(throwable: Throwable) {
        internalState.value = Failure(errorFactory.create(throwable), currentValue())
    }
}
