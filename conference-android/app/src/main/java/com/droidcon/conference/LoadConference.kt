package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.StateMachine

class LoadConference(
    private val stateMachine: StateMachine<Conference, GatewayError>,
    private val conferenceGateway: ConferenceGateway
) {
    suspend operator fun invoke() {
        runCatching {
            stateMachine.moveToLoading()
            stateMachine.moveToLoaded(conferenceGateway.getConference())
        }.onFailure { throwable ->
            stateMachine.moveToError(throwable)
        }
    }
}
