package com.droidcon.conference

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droidcon.android.MainActivity
import com.droidcon.testing.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConferenceEndToEndTest {

    @Test
    fun givenRegisteredConferenceWhenApplicationStartsShowConferenceName() {

        ActivityScenario.launch(MainActivity::class.java)

        Thread.sleep(2000)

        onView(ViewMatchers.withId(R.id.conferenceName)).check(matches(withText("Droidcon")))
    }
}
