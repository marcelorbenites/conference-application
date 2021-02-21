package com.droidcon.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateMachine<T, E>(
    private val errorFactory: ErrorFactory<E>,
    private val initialState: State<T, E> = State(State.Name.IDLE)
) {

    private val internalState: MutableStateFlow<State<T, E>> = MutableStateFlow(initialState)

    val state = internalState.asStateFlow()

    fun moveToLoading() {
        internalState.value = State(State.Name.LOADING, initialState.value, null)
    }

    fun moveToLoaded(value: T) {
        internalState.value = State(State.Name.LOADED, value, null)
    }

    fun moveToError(throwable: Throwable) {
        internalState.value =
            State(State.Name.ERROR, initialState.value, errorFactory.create(throwable))
    }
}
