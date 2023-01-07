package com.example.perforatormobile.app.fragments.peers_review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.app.fragments.verify_chosen_peers.VerifiedPeersListAdapter
import com.example.perforatormobile.app.fragments.verify_chosen_peers.VerifyChosenPeersFragment
import com.example.perforatormobile.databinding.FragmentPeersReviewBinding

class PeersReviewFragment: Fragment(R.layout.fragment_peers_review) {
    private val viewModel: PeersReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPeersReviewBinding.inflate(inflater, container, false)

        val reviewedPeersAdapter = VerifiedPeersListAdapter { userId ->
            val arg = Bundle().apply {
                putInt(PEER_ID, userId)
            }
            findNavController().navigate(R.id.action_navigation_peers_review_to_grade_questions, arg)
        }
        reviewedPeersAdapter.submitList(viewModel.peersToReview)
        binding.peersToReviewRecyclerView.adapter = reviewedPeersAdapter

        return binding.root
    }

    companion object {
        const val PEER_ID = "peer_id"
    }
}