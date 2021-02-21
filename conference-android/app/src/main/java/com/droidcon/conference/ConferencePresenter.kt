package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Error

class ConferencePresenter(state: Flow<State<Conference, GatewayError>>) {
    val viewModel = state.map { state ->
        when (state) {
            is Idle -> {
                ConferenceViewModel(
                    showLoading = false,
                    showError = false,
                    hideName = true,
                    showRetry = false
                )
            }
            is Loading<Conference> -> {
                val name = state.value?.name ?: ""
                ConferenceViewModel(
                    name = name,
                    showLoading = true,
                    showError = false,
                    hideName = name.isBlank(),
                    showRetry = false
                )
            }
            is Loaded<Conference> -> {
                ConferenceViewModel(
                    name = state.value.name,
                    showLoading = false,
                    showError = false,
                    hideName = false,
                    showRetry = false
                )
            }
            is Failure<Conference, GatewayError> -> {
                val name = state.value?.name ?: ""
                ConferenceViewModel(
                    name = name,
                    showLoading = false,
                    showError = true,
                    hideName = name.isBlank(),
                    showRetry = state.error.network
                )
            }
        }
    }
}
