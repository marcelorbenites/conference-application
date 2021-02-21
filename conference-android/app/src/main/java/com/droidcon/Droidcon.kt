package com.droidcon

import com.droidcon.conference.*
import com.droidcon.gateway.GatewayError
import com.droidcon.gateway.GatewayErrorFactory
import com.droidcon.state.StateMachine
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient

class Droidcon(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val baseUrl: String = "http://10.0.2.2:8080/"
) : DependencyInjector {

    private val scope = CoroutineScope(dispatcher)

    private val conferenceStateMachine =
        StateMachine<Conference, GatewayError>(GatewayErrorFactory())

    override fun conferenceController() = ConferenceController(
        scope,
        LoadConference(
            conferenceStateMachine,
            OkHttpConferenceGateway(baseUrl, OkHttpClient())
        )
    )

    override fun conferencePresenter() = ConferencePresenter(conferenceStateMachine.state)
}
