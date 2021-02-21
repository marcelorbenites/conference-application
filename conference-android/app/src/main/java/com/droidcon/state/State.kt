package com.droidcon.state

sealed class State<out T, out E>
object Idle : State<Nothing, Nothing>()
data class Loading<T>(val value: T? = null) : State<T, Nothing>()
data class Loaded<T>(val value: T) : State<T, Nothing>()
data class Failure<T, E>(val error: E, val value: T? = null) : State<T, E>()
