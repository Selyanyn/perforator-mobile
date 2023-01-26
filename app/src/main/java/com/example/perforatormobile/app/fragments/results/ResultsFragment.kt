package com.example.perforatormobile.app.fragments.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.app.ActivityViewModel
import com.example.perforatormobile.app.fragments.peers_review.PeersReviewFragment
import com.example.perforatormobile.app.fragments.verify_chosen_peers.VerifiedPeersListAdapter
import com.example.perforatormobile.databinding.FragmentResultsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultsFragment: Fragment(R.layout.fragment_results) {
    private val viewModel: ResultsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentResultsBinding.inflate(inflater, container, false)

        if (viewModel.subordinates.isEmpty()) {
            binding.noSubordinatesText.visibility = View.VISIBLE
            binding.mySubordinatesResultsRecyclerView.visibility = View.GONE
        } else {
            val subordinatesResultsAdapter = VerifiedPeersListAdapter { userId ->
                navigateToUserResult(userId)
            }
            binding.mySubordinatesResultsRecyclerView.adapter = subordinatesResultsAdapter
            subordinatesResultsAdapter.submitList(viewModel.subordinates)
        }

        binding.watchMyResults.setOnClickListener {
            navigateToUserResult(ViewModelProvider(requireActivity())[ActivityViewModel::class.java].getUserId())
        }

        return binding.root
    }

    private fun navigateToUserResult(userId: Int)
    {
        val arg = Bundle().apply {
            putInt(PeersReviewFragment.PEER_ID, userId)
        }
        findNavController().navigate(R.id.action_navigation_results_to_user_results, arg)
    }
}