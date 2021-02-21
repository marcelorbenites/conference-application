package com.droidcon.conference

data class ConferenceViewModel(
    val name: String = "",
    val showLoading: Boolean = false,
    val showError: Boolean = false,
    val hideName: Boolean = false,
    val showRetry: Boolean = false
)