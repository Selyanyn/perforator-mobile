package com.example.perforatormobile.app.fragments.self_review

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.perforatormobile.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelfReviewFragment: Fragment(R.layout.fragment_self_review) {

    private val viewModel: SelfReviewViewModel by viewModels()
}