package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConferencePresenter(state: Flow<State<Conference, GatewayError>>) {
    val viewModel = state.map { state ->
        val viewModel = ConferenceViewModel()
        when (state.name) {
            State.Name.IDLE -> {
                viewModel.showLoading = false
                viewModel.showError = false
                viewModel.hideName = true
                viewModel.showRetry = false
            }
            State.Name.LOADING -> {
                viewModel.showLoading = true
                viewModel.showError = false
                viewModel.hideName = true
                viewModel.showRetry = false
            }
            State.Name.LOADED -> {
                val conference = state.value!!
                viewModel.showLoading = false
                viewModel.showError = false
                viewModel.hideName = false
                viewModel.showRetry = false
                viewModel.name = conference.name
            }
            State.Name.ERROR -> {
                viewModel.showLoading = false
                viewModel.showError = true
                viewModel.hideName = true
                viewModel.showRetry = true
            }
        }
        viewModel
    }
}
