package com.example.perforatormobile.app.fragments.self_review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.app.fragments.self_review.SelfReviewFragment.Companion.CHOSEN_PEERS
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.PersonList
import com.example.perforatormobile.domain.usecases.self_review.GetSelfReviewFormUseCase
import com.example.perforatormobile.domain.usecases.self_review.SearchNewPeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelfReviewViewModel @Inject constructor(
    private val getSelfReviewFormUseCase: GetSelfReviewFormUseCase,
    private val searchNewPeersUseCase: SearchNewPeersUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {

    val selfReview = getSelfReviewFormUseCase()
    val chosenPeers: MutableList<Person> = if (stateHandle.contains(CHOSEN_PEERS))
        stateHandle.get<PersonList>(CHOSEN_PEERS)!!.peersList
        else mutableListOf(Person(userId = 1, profileId = 1, username = "Pet owner",
            photoUrl = "https://media-be.chewy.com/wp-content/uploads/shutterstock_492574771.jpg", sbis="125"))
    private val searchedPeers = mutableListOf<Person>()

    fun doOnSearchPeersTextChanged(firstName: String) {
        viewModelScope.launch {
            searchedPeers.clear()
            searchedPeers.addAll(0, searchNewPeersUseCase(firstName))
        }
    }
}