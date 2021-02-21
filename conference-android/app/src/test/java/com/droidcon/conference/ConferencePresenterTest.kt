package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.Failure
import com.droidcon.state.Loaded
import com.droidcon.state.Loading
import com.droidcon.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class ConferencePresenterTest {

    @Test
    fun `Given a loaded conference When state is updated Then show conference name`() =
        runBlocking {
            val state: Flow<State<Conference, GatewayError>> = flow {
                emit(Loaded(Conference(1, "Droidcon")))
            }
            val presenter = ConferencePresenter(state)

            val viewModel = presenter.viewModel.first()

            assertEquals(false, viewModel.showError)
            assertEquals(false, viewModel.showLoading)
            assertEquals(false, viewModel.hideName)
            assertEquals(false, viewModel.showRetry)
            assertEquals("Droidcon", viewModel.name)
        }

    @Test
    fun `Given a loading state When state is updated Then show loading`() = runBlocking {
        val state: Flow<State<Conference, GatewayError>> = flow {
            emit(Loading<Conference>())
        }
        val presenter = ConferencePresenter(state)

        val viewModel = presenter.viewModel.first()

        assertEquals(false, viewModel.showError)
        assertEquals(true, viewModel.showLoading)
        assertEquals(true, viewModel.hideName)
        assertEquals(false, viewModel.showRetry)
        assertEquals("", viewModel.name)
    }

    @Test
    fun `Given a error state When state is updated Then show error And show retry`() = runBlocking {
        val state: Flow<State<Conference, GatewayError>> = flow {
            emit(Failure<Conference, GatewayError>(GatewayError(IOException())))
        }
        val presenter = ConferencePresenter(state)

        val viewModel = presenter.viewModel.first()

        assertEquals(true, viewModel.showError)
        assertEquals(false, viewModel.showLoading)
        assertEquals(true, viewModel.hideName)
        assertEquals(true, viewModel.showRetry)
        assertEquals("", viewModel.name)
    }
}
