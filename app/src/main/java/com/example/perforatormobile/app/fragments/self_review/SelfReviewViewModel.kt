package com.example.perforatormobile.app.fragments.self_review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.usecases.self_review.GetSelfReviewFormUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Inject

@HiltViewModel
class SelfReviewViewModel @Inject constructor(
    getSelfReviewFormUseCase: GetSelfReviewFormUseCase
) : ViewModel() {

    val selfReview = getSelfReviewFormUseCase()
    val peers = mutableListOf(Person(userId = 1, profileId = 1, username = "Pet owner",
                    photoUrl = "https://media-be.chewy.com/wp-content/uploads/shutterstock_492574771.jpg", sbis="125"))
}