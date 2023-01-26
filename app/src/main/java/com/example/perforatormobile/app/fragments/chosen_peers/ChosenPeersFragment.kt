package com.example.perforatormobile.app.fragments.chosen_peers

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
import com.example.perforatormobile.app.fragments.choose_new_peers.ChoosePeersFragment
import com.example.perforatormobile.app.fragments.choose_new_peers.ChoosePeersFragment.Companion.NAVIGATE_ACTION
import com.example.perforatormobile.app.fragments.choose_new_peers.PeersListAdapter
import com.example.perforatormobile.app.fragments.verify_chosen_peers.VerifyChosenPeersFragment
import com.example.perforatormobile.databinding.FragmentChosenPeersBinding
import com.example.perforatormobile.domain.enums.ParentFragmentEnum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChosenPeersFragment: Fragment(R.layout.fragment_chosen_peers) {
    private val viewModel: ChosenPeersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Clear mess, conjoin with SelfReview if possible
        val binding = FragmentChosenPeersBinding.inflate(inflater, container, false)

        val peersAdapter = PeersListAdapter { position ->
            viewModel.chosenPeers.value = viewModel.chosenPeers.value.filterIndexed { index, person ->
                index != position
            }
        }
        binding.selfReviewPeersRecyclerView.adapter = peersAdapter

        binding.addPeersButton.setOnClickListener {
            val arg = Bundle().apply {
                putInt(PARENT_FRAGMENT, ParentFragmentEnum.VERIFY_PEERS.ordinal)
                putInt(NAVIGATE_ACTION, R.id.action_navigation_choose_peers_to_chosen_peers)
                putInt(VerifyChosenPeersFragment.SUBORDINATE_ID, viewModel.currentUserId)
            }
            findNavController().navigate(R.id.action_navigation_chosen_peers_to_choose_peers, arg)
        }

        binding.verifyPeersButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.verifyAndSavePeers()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.chosenPeers
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    peersAdapter.submitList(it)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.arePeersSaved
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    if (it) {
                        findNavController().navigate(R.id.action_navigation_chosen_peers_to_verify_peers)
                    }
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchPeers()
        }

        return binding.root
    }

    companion object {
        const val PARENT_FRAGMENT = "parentFragment"
    }
}