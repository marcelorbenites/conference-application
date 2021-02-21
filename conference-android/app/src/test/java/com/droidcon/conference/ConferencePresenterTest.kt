package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class ConferencePresenterTest {

    @Test
    fun `Given a loaded conference When state is updated Then show conference name`() =
        runBlockingTest {
            val state: Flow<State<Conference, GatewayError>> = flow {
                emit(State(State.Name.LOADED, Conference(1, "Droidcon")))
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
    fun `Given a loading state When state is updated Then show loading`() = runBlockingTest {
        val state: Flow<State<Conference, GatewayError>> = flow {
            emit(State(State.Name.LOADING))
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
    fun `Given a error state When state is updated Then show error And show retry`() =
        runBlockingTest {
            val state: Flow<State<Conference, GatewayError>> = flow {
                emit(State(State.Name.ERROR))
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
