package com.droidcon.conference

class ConferenceController(private val loadConference: LoadConference) {
    fun onRetry() { loadConference() }
}