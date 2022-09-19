package com.example.perforatormobile.app.fragments.self_review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.perforatormobile.R
import com.example.perforatormobile.databinding.FragmentSelfReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SelfReviewFragment: Fragment(R.layout.fragment_self_review) {

    private val viewModel: SelfReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelfReviewBinding.inflate(inflater, container, false)

        val adapter = GradesListAdapter()
        binding.selfReviewRecyclerView.adapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}