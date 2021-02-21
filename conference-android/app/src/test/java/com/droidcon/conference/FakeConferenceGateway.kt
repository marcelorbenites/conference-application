package com.droidcon.conference

class FakeConferenceGateway(private val conference: Conference? = null) : ConferenceGateway {
    override suspend fun getConference(): Conference = conference!!
}
