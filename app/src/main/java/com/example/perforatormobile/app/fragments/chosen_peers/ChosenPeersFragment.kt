package com.example.perforatormobile.app.fragments.chosen_peers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.app.fragments.choose_new_peers.ChoosePeersFragment.Companion.NAVIGATE_ACTION
import com.example.perforatormobile.app.fragments.choose_new_peers.PeersListAdapter
import com.example.perforatormobile.app.fragments.verify_chosen_peers.VerifyChosenPeersFragment
import com.example.perforatormobile.databinding.FragmentChosenPeersBinding

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
            viewModel.chosenPeers.removeAt(position)
        }
        binding.selfReviewPeersRecyclerView.adapter = peersAdapter
        peersAdapter.submitList(viewModel.chosenPeers)

        binding.addPeersButton.setOnClickListener {
            val arg = Bundle().apply {
                putInt(NAVIGATE_ACTION, R.id.action_navigation_choose_peers_to_chosen_peers)
            }
            findNavController().navigate(R.id.action_navigation_chosen_peers_to_choose_peers, arg)
        }

        binding.verifyPeersButton.setOnClickListener {
            // TODO: Send POST request to update subordinate's peer list
            // No need to Bundle because will be updating values anyway
            findNavController().navigate(R.id.action_navigation_choose_peers_to_self_review)
        }

        return binding.root
    }
}