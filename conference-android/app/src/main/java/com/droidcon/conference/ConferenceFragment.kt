package com.droidcon.conference

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.droidcon.android.injector
import com.droidcon.testing.R
import kotlinx.android.synthetic.main.fragment_conference.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ConferenceFragment : Fragment(R.layout.fragment_conference) {

    private val conferencePresenter by lazy {
        requireContext().injector().conferencePresenter
    }

    private val conferenceController by lazy {
        requireContext().injector().conferenceController
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            conferencePresenter.viewModel.collect { viewModel ->
                if (viewModel.hideName) {
                    conferenceName.visibility = View.GONE
                } else {
                    conferenceName.text = viewModel.name
                    conferenceName.visibility = View.VISIBLE
                }

                errorText.visibility =
                    if (viewModel.showError) View.VISIBLE else View.GONE
                retryButton.visibility =
                    if (viewModel.showRetry) View.VISIBLE else View.GONE
                loadingProgressBar.visibility =
                    if (viewModel.showLoading) View.VISIBLE else View.GONE
            }
        }
        retryButton.setOnClickListener {
            conferenceController.onRetry()
        }
    }
}
