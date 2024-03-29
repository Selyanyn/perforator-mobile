package com.example.perforatormobile.app.fragments.self_review

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.app.fragments.choose_new_peers.PeersListAdapter
import com.example.perforatormobile.app.fragments.chosen_peers.ChosenPeersFragment
import com.example.perforatormobile.databinding.FragmentSelfReviewBinding
import com.example.perforatormobile.domain.entities.PersonList
import com.example.perforatormobile.domain.enums.ParentFragmentEnum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SelfReviewFragment: Fragment(R.layout.fragment_self_review) {

    private val viewModel: SelfReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSelfReviewBinding.inflate(inflater, container, false)

        val gradesAdapter = GradesListAdapter(viewModel::updateGrades)
        binding.selfReviewRecyclerView.adapter = gradesAdapter

        val peersAdapter = PeersListAdapter { position ->
            viewModel.chosenPeers.value = viewModel.chosenPeers.value!!.filterIndexed { index, person ->
                position != index
            }.toMutableList()
        }
        binding.selfReviewPeersRecyclerView.adapter = peersAdapter
        binding.selfReviewRecyclerView.isNestedScrollingEnabled = true
        //peersAdapter.submitList(viewModel.chosenPeers.value)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.chosenPeers
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    peersAdapter.submitList(it)
                }
        }

        binding.addPeersButton.setOnClickListener {
            val arg = Bundle().apply {
                putInt(ChosenPeersFragment.PARENT_FRAGMENT, ParentFragmentEnum.SELF_REVIEW.ordinal)
            }
            findNavController().navigate(R.id.action_navigation_self_review_to_choose_peers, arg)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selfReview
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    if (it!!.isDraft) {
                        gradesAdapter.submitList(it.grades)
                    } else {
                        binding.selfReviewRoot.visibility = View.GONE
                        binding.selfReviewInactiveTitle.visibility = View.VISIBLE
                    }
                }
        }

        binding.saveSelfReviewForm.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.saveReview(true)
            }
        }

        binding.saveSelfReviewFormAsDraft.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.saveReview(false)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getSelfReview()
            viewModel.fetchAllChosenPeers()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isSelfReviewSaved
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    if (it) {
                        findNavController().navigate(R.id.action_navigation_self_review_to_peers_review)
                    }
                }
        }

        return binding.root
    }

    companion object {
        const val CHOSEN_PEERS = "chosenPeers"
    }
}