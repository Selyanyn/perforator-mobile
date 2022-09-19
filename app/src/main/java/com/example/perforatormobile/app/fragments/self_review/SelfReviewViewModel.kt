package com.example.perforatormobile.app.fragments.self_review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.domain.usecases.self_review.GetSelfReviewFormUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Inject

@HiltViewModel
class SelfReviewViewModel @Inject constructor(
    getSelfReviewFormUseCase: GetSelfReviewFormUseCase
) : ViewModel() {

    val selfReview = getSelfReviewFormUseCase()

}