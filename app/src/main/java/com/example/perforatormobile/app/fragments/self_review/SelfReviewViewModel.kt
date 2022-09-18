package com.example.perforatormobile.app.fragments.self_review

import androidx.lifecycle.ViewModel
import com.example.perforatormobile.domain.usecases.self_review.GetSelfReviewFormUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelfReviewViewModel @Inject constructor(
    getSelfReviewFormUseCase: GetSelfReviewFormUseCase
) : ViewModel() {

    private val selfReview = getSelfReviewFormUseCase()

}