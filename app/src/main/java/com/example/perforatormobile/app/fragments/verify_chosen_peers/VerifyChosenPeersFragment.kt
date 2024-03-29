package com.example.perforatormobile.app.fragments.verify_chosen_peers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.databinding.FragmentVerifyPeersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class VerifyChosenPeersFragment: Fragment(R.layout.fragment_verify_peers) {

    private val viewModel: VerifyChosenPeersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVerifyPeersBinding.inflate(inflater, container, false)

        val subordinatesNotFinishedSelfReviewAdapter = VerifiedPeersListAdapter { _ -> }
        binding.usersNotSentSelfReviewRecyclerView.adapter = subordinatesNotFinishedSelfReviewAdapter

        val notVerifiedPeersListAdapter = VerifiedPeersListAdapter { userId ->
            val arg = Bundle().apply {
                putInt(SUBORDINATE_ID, userId)
            }
            findNavController().navigate(R.id.action_navigation_verify_peers_to_chosen_peers, arg)
        }
        binding.notVerifiedUsersRecyclerView.adapter = notVerifiedPeersListAdapter

        val verifiedPeersListAdapter = VerifiedPeersListAdapter { _ -> }
        binding.verifiedUsersRecyclerView.adapter = verifiedPeersListAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.approvedSubordinates
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    verifiedPeersListAdapter.submitList(it)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.unapprovedSubordinates
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    notVerifiedPeersListAdapter.submitList(it)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.notFinishedSubordinates
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    subordinatesNotFinishedSelfReviewAdapter.submitList(it)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchSubordinates()
        }

        return binding.root
    }

    companion object {
        const val SUBORDINATE_ID = "subordinateId"
    }
}