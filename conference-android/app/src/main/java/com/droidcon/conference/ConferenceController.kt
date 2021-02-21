package com.droidcon.conference

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ConferenceController(
    private val scope: CoroutineScope,
    private val loadConference: LoadConference
) {
    init {
        scope.launch { loadConference() }
    }

    fun onRetry() = scope.launch { loadConference() }
}