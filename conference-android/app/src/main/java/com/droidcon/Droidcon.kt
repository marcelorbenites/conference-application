package com.droidcon

import com.droidcon.conference.*
import com.droidcon.gateway.GatewayError
import com.droidcon.gateway.GatewayErrorFactory
import com.droidcon.state.StateMachine
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class Droidcon(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    baseUrl: String = "http://10.0.2.2:8080/"
) : DependencyInjector {

    private val scope = CoroutineScope(dispatcher)

    private val conferenceStateMachine =
        StateMachine<Conference, GatewayError>(GatewayErrorFactory())

    private val loadConference = LoadConference(
        conferenceStateMachine,
        OkHttpConferenceGateway(baseUrl, OkHttpClient())
    )

    override val conferenceController = ConferenceController(scope, loadConference)

    override val conferencePresenter = ConferencePresenter(conferenceStateMachine.state)

    fun start() {
        scope.launch { loadConference() }
    }
}
