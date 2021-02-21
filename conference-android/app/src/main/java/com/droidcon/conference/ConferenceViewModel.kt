package com.droidcon.conference

data class ConferenceViewModel(
    var name: String = "",
    var showLoading: Boolean = false,
    var showError: Boolean = false,
    var hideName: Boolean = false,
    var showRetry: Boolean = false
)