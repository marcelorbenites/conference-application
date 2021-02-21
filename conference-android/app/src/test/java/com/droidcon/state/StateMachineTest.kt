package com.droidcon.state

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class StateMachineTest {

    @Test
    fun `Given an idle state When move to loading is called Then current state should return loading with no value And no error`() =
        runBlocking {
            val stateMachine = StateMachine<TestValue, TestError>(TestErrorFactory())

            stateMachine.moveToLoading()

            assertEquals(Loading<TestValue>(), stateMachine.state.first())
        }

    @Test
    fun `Given an idle state When move to loaded is called with value Then return loaded with value And no error`() =
        runBlocking {
            val stateMachine = StateMachine<TestValue, TestError>(TestErrorFactory())
            val value = TestValue()

            stateMachine.moveToLoaded(value)

            assertEquals(Loaded(value), stateMachine.state.first())
        }

    @Test
    fun `Given an loaded state When move to error is called with error Then should return error state with error`() =
        runBlocking {
            val error = TestError()
            val stateMachine = StateMachine<TestValue, TestError>(TestErrorFactory(error))

            stateMachine.moveToError(IllegalStateException())

            assertEquals(
                Failure<TestValue, TestError>(error = error),
                stateMachine.state.first()
            )
        }
}

class TestValue

class TestError

class TestErrorFactory(private val error: TestError? = null) : ErrorFactory<TestError> {
    override fun create(throwable: Throwable) = error!!
}
