package com.droidcon.conference

import com.droidcon.dispatcher.FakeDispatcher
import com.droidcon.gateway.GatewayError
import com.droidcon.state.State
import com.droidcon.state.StateMachine
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class LoadConferenceTest {

    @Test
    fun `Given a registered conference When load conference is called Then should emit loaded state with conference`() =
        runBlockingTest {
            val conference = Conference(1, "Droidcon")

            val stateMachine = StateMachine<Conference, GatewayError>(FakeGatewayErrorFactory())

            LoadConference(
                FakeDispatcher(),
                stateMachine,
                FakeConferenceGateway(conference)
            )()

            assertEquals(
                State<Conference, GatewayError>(State.Name.LOADED, conference),
                stateMachine.state.first()
            )
        }
}
