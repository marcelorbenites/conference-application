package com.droidcon

import com.droidcon.conference.ConferenceController
import com.droidcon.conference.ConferencePresenter
import com.droidcon.conference.ConferenceViewModel
import com.droidcon.conference.LoadConference
import kotlinx.coroutines.flow.Flow

interface DependencyInjector {
    fun conferencePresenter(): ConferencePresenter
    fun conferenceController(): ConferenceController
}
