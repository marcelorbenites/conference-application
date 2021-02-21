package com.droidcon.conference

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.droidcon.DependencyManager
import com.droidcon.android.ViewContainer
import com.droidcon.testing.R
import kotlinx.android.synthetic.main.fragment_conference.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ConferenceFragment : Fragment() {

    private var dependencyManager: DependencyManager? = null

    private val conferencePresenter by lazy {
        dependencyManager!!.conferencePresenter
    }

    private val conferenceController by lazy {
        dependencyManager!!.conferenceController
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dependencyManager = (context as ViewContainer).dependencyManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_conference, container, false)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            conferencePresenter.viewModel.collect { conferenceViewModel ->
                if (conferenceViewModel.hideName) {
                    conferenceName.visibility = View.GONE
                } else {
                    conferenceName.text = conferenceViewModel.name
                    conferenceName.visibility = View.VISIBLE
                }

                errorText.visibility =
                    if (conferenceViewModel.showError) View.VISIBLE else View.GONE
                retryButton.visibility =
                    if (conferenceViewModel.showRetry) View.VISIBLE else View.GONE
                loadingProgressBar.visibility =
                    if (conferenceViewModel.showLoading) View.VISIBLE else View.GONE
            }
        }
        retryButton.setOnClickListener {
            conferenceController.onRetry()
        }
    }

    override fun onDetach() {
        dependencyManager = null
        super.onDetach()
    }
}
