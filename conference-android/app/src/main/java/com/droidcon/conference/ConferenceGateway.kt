package com.droidcon.conference

interface ConferenceGateway {
    suspend fun getConference(): Conference
}
