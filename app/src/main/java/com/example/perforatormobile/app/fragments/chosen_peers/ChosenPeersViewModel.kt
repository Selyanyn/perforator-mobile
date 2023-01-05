package com.example.perforatormobile.app.fragments.chosen_peers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.perforatormobile.app.fragments.self_review.SelfReviewFragment
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.PersonList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChosenPeersViewModel @Inject constructor(
    stateHandle: SavedStateHandle
): ViewModel() {
    val chosenPeers: MutableList<Person> = if (stateHandle.contains(SelfReviewFragment.CHOSEN_PEERS))
        stateHandle.get<PersonList>(SelfReviewFragment.CHOSEN_PEERS)!!.peersList
    else mutableListOf(
        Person(userId = 1, profileId = 1, username = "Pet owner",
        photoUrl = "https://media-be.chewy.com/wp-content/uploads/shutterstock_492574771.jpg", sbis="125")
    )

}