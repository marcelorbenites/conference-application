package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConferencePresenter(state: Flow<State<Conference, GatewayError>>) {
    val viewModel = state.map { state ->
        when (state.name) {
            State.Name.IDLE -> {
                ConferenceViewModel(
                    showLoading = false,
                    showError = false,
                    hideName = true,
                    showRetry = false
                )
            }
            State.Name.LOADING -> {
                ConferenceViewModel(
                    showLoading = true,
                    showError = false,
                    hideName = true,
                    showRetry = false
                )
            }
            State.Name.LOADED -> {
                val conference = state.value!!
                ConferenceViewModel(
                    name = conference.name,
                    showLoading = false,
                    showError = false,
                    hideName = false,
                    showRetry = false
                )
            }
            State.Name.ERROR -> {
                ConferenceViewModel(
                    showLoading = false,
                    showError = true,
                    hideName = true,
                    showRetry = true
                )
            }
        }
    }
}
