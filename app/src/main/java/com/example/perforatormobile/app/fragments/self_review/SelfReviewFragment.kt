package com.example.perforatormobile.app.fragments.self_review

import android.app.ActionBar
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.content.getSystemService
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perforatormobile.R
import com.example.perforatormobile.databinding.FragmentSelfReviewBinding
import com.google.android.material.textfield.TextInputEditText
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
            viewModel.chosenPeers.removeAt(position)
        }
        binding.selfReviewPeersRecyclerView.adapter = peersAdapter
        peersAdapter.submitList(viewModel.chosenPeers)

        binding.addPeersButton.setOnClickListener {
            val popUpView = LayoutInflater.from(context).inflate(R.layout.pop_up_peers_search, container, false)
            val popupWindow = providePeerPopup(popUpView)
            val location = IntArray(2)
            binding.addPeersButton.getLocationOnScreen(location)
            popupWindow.showAtLocation(binding.addPeersButton, Gravity.NO_GRAVITY, location[0], location[1])
        }

        return binding.root
    }

    private fun providePeerPopup(popupView: View): PopupWindow
    {
        val popUpWindow = PopupWindow(popupView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
        val searchInput = popupView.findViewById<TextInputEditText>(R.id.peer_search_edit_text)
        searchInput.doOnTextChanged { text, _, _, _ ->
            viewModel.doOnSearchPeersTextChanged(text.toString())
        }
        val peersList = popupView.findViewById<RecyclerView>(R.id.peer_search_recycler_view)
        val adapter = PeersListAdapter { _ -> }
        peersList.adapter = adapter
        peersList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return popUpWindow
    }
}