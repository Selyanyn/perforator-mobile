package com.example.perforatormobile.app.fragments.self_review

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.app.fragments.choose_new_peers.PeersListAdapter
import com.example.perforatormobile.databinding.FragmentSelfReviewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelfReviewFragment: Fragment(R.layout.fragment_choose_peers) {

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
            viewModel.chosenPeers.removeAt(position)
        }
        binding.selfReviewPeersRecyclerView.adapter = peersAdapter
        peersAdapter.submitList(viewModel.chosenPeers)

        binding.addPeersButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_self_review_to_choose_peers)
        }

        return binding.root
    }

    companion object {
        const val CHOSEN_PEERS = "chosenPeers"
    }

    /*private fun providePeerPopup(popupView: View): PopupWindow
    {
        val popUpWindow = PopupWindow(popupView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        val searchInput = popupView.findViewById<TextInputEditText>(R.id.peer_search_edit_text)
        searchInput.doOnTextChanged { text, _, _, _ ->
            viewModel.doOnSearchPeersTextChanged(text.toString())
        }
        val peersList = popupView.findViewById<RecyclerView>(R.id.peer_search_recycler_view)
        val adapter = PeersListAdapter { _ -> }
        peersList.adapter = adapter
        peersList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        popUpWindow.isFocusable = false
        popUpWindow.isOutsideTouchable = false
        return popUpWindow
    }*/
}