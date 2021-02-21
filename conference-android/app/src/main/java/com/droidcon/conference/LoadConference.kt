package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.Dispatcher
import com.droidcon.state.ErrorFactory
import com.droidcon.state.StateMachine

class LoadConference(
    private val dispatcher: Dispatcher,
    private val stateMachine: StateMachine<Conference, GatewayError>,
    private val conferenceGateway: ConferenceGateway
) {
    operator fun invoke() {
        dispatcher.dispatch({
            stateMachine.moveToLoading()
            stateMachine.moveToLoaded(conferenceGateway.getConference())
        }, { throwable ->
            stateMachine.moveToError(throwable)
        })
    }
}
