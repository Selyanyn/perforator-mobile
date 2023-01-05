package com.example.perforatormobile.app.fragments.chosen_peers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.app.fragments.choose_new_peers.PeersListAdapter
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
            findNavController().navigate(R.id.action_navigation_self_review_to_choose_peers)
        }

        return binding.root
    }
}