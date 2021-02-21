package com.droidcon

import com.droidcon.conference.*
import com.droidcon.gateway.GatewayError
import com.droidcon.gateway.GatewayErrorFactory
import com.droidcon.state.State
import com.droidcon.state.StateMachine
import kotlinx.coroutines.flow.map

class DroidconApplication private constructor(
    lazyDispatcherFactory: Lazy<DispatcherFactory>,
    lazyConferenceGateway: Lazy<ConferenceGateway>
) : DependencyManager {

    private val conferenceStateMachine =
        StateMachine<Conference, GatewayError>(GatewayErrorFactory())

    private val loadConference = LoadConference(
        lazyDispatcherFactory.value.createSerialDispatcher("Conference"),
        conferenceStateMachine,
        lazyConferenceGateway.value
    )

    override val conferenceController = ConferenceController(loadConference)

    override val conferencePresenter = ConferencePresenter(conferenceStateMachine.state)

    private fun start() { loadConference() }

    class Builder {

        private var lazyDispatcherFactory: Lazy<DispatcherFactory>? = null
        private var lazyConferenceGateway: Lazy<ConferenceGateway>? = null

        fun registerDispatcherFactory(lazyDispatcherFactory: Lazy<DispatcherFactory>): Builder {
            this.lazyDispatcherFactory = lazyDispatcherFactory
            return this
        }

        fun registerConferenceGateway(lazyConferenceGateway: Lazy<ConferenceGateway>): Builder {
            this.lazyConferenceGateway = lazyConferenceGateway
            return this
        }

        fun start(): DroidconApplication {
            val application =
                DroidconApplication(lazyDispatcherFactory!!, lazyConferenceGateway!!)
            application.start()
            return application
        }
    }
}
