package com.example.perforatormobile.app.fragments.self_review

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.perforatormobile.R
import com.example.perforatormobile.databinding.FragmentSelfReviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelfReviewFragment: Fragment(R.layout.fragment_self_review) {

    private val viewModel: SelfReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSelfReviewBinding.inflate(inflater, container, false)

        val gradesAdapter = GradesListAdapter()
        binding.selfReviewRecyclerView.adapter = gradesAdapter
        gradesAdapter.submitList(viewModel.selfReview.grades)

        val peersAdapter = PeersListAdapter { position ->
            viewModel.peers.removeAt(position)
        }
        binding.selfReviewPeersRecyclerView.adapter = peersAdapter
        peersAdapter.submitList(viewModel.peers)

        return binding.root
    }
}