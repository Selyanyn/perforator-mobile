package com.example.perforatormobile.app.fragments.choose_new_peers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.app.fragments.self_review.SelfReviewFragment.Companion.CHOSEN_PEERS
import com.example.perforatormobile.databinding.FragmentChoosePeersBinding
import com.example.perforatormobile.domain.entities.PersonList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ChoosePeersFragment: Fragment(R.layout.fragment_choose_peers) {

    private val viewModel: ChoosePeersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChoosePeersBinding.inflate(inflater, container, false)

        val peersAdapter = PeersListAdapter { position ->
            viewModel.removeAndSavePeerAtPosition(position)
        }
        binding.peerSearchRecyclerView.adapter = peersAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchedPeers
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { newPotentialPeers ->
                    Log.e("XXX", "update fired$newPotentialPeers")
                    peersAdapter.submitList(newPotentialPeers)
                    Log.e("XXX", "${peersAdapter.currentList}")
                }
        }

        binding.peerSearchEditText.doOnTextChanged {text, _, _, _ ->
            viewModel.doOnSearchPeersTextChanged(text.toString())
        }

        binding.saveChosenPeersButton.setOnClickListener {
            val arg = Bundle().apply {
                putParcelable(CHOSEN_PEERS, PersonList(viewModel.chosenPeers))
            }
            findNavController().navigate(viewModel.navigateAction, arg)
        }

        return binding.root
    }

    companion object {
        const val NAVIGATE_ACTION = "navigate_action"
        const val NAVIGATE_ACTION_TO_SELF_REVIEW = R.id.action_navigation_choose_peers_to_self_review
        const val NAVIGATE_ACTION_TO_CHOSEN_PEERS = R.id.action_navigation_choose_peers_to_chosen_peers
    }
}