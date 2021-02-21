package com.droidcon.conference

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.droidcon.Droidcon
import com.droidcon.android.AndroidApplication
import com.droidcon.android.MainActivity
import com.droidcon.testing.R
import kotlinx.coroutines.Dispatchers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConferenceComponentTest {

    @Test
    fun `Given a registered conference When conference screen appears Then show conference name`() {
        val server = MockWebServer()
        server.start()
        val baseUrl = server.url("/").toString()

        val json = """
            [{
              "id": "1",
              "name": "Droidcon"
            }]
        """

        server.enqueue(MockResponse().setResponseCode(200).setBody(json))

        (InstrumentationRegistry.getInstrumentation().targetContext as AndroidApplication)
            .droidcon = Droidcon(Dispatchers.Unconfined, baseUrl)
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.conferenceName)).check(matches(ViewMatchers.withText("Droidcon")))

        server.shutdown()
    }
}
