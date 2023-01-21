package com.example.perforatormobile.app.fragments.self_review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.app.fragments.self_review.SelfReviewFragment.Companion.CHOSEN_PEERS
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.PersonList
import com.example.perforatormobile.domain.entities.Review
import com.example.perforatormobile.domain.usecases.self_review.GetSelfReviewFormUseCase
import com.example.perforatormobile.domain.usecases.self_review.SearchNewPeersUseCase
import com.example.perforatormobile.domain.usecases.self_review.getAllCurrentUserPeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelfReviewViewModel @Inject constructor(
    private val getSelfReviewFormUseCase: GetSelfReviewFormUseCase,
    private val getAllCurrentUserPeersUseCase: getAllCurrentUserPeersUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {

    val selfReview : MutableStateFlow<Review?> = MutableStateFlow(null)
    val chosenPeers: MutableStateFlow<MutableList<Person>?> = MutableStateFlow(mutableListOf())

    suspend fun getSelfReview()
    {
        selfReview.value = getSelfReviewFormUseCase().body()
    }

    suspend fun fetchAllChosenPeers()
    {
        chosenPeers.value = getAllCurrentUserPeersUseCase().body()?.toMutableList()
    }
}