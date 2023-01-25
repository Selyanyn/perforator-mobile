package com.example.perforatormobile.app.fragments.chosen_peers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.perforatormobile.app.fragments.self_review.SelfReviewFragment
import com.example.perforatormobile.app.fragments.verify_chosen_peers.VerifyChosenPeersFragment
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.PersonList
import com.example.perforatormobile.domain.enums.ParentFragmentEnum
import com.example.perforatormobile.domain.usecases.my_team.SaveMyPeersUseCase
import com.example.perforatormobile.domain.usecases.verify_peers.SavePeersOfACurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ChosenPeersViewModel @Inject constructor(
    private val savePeersOfACurrentUser: SavePeersOfACurrentUser,
    stateHandle: SavedStateHandle
): ViewModel() {
    val currentUserId = stateHandle.get<Int>(VerifyChosenPeersFragment.SUBORDINATE_ID)!!
    val chosenPeers: MutableList<Person> = if (stateHandle.contains(SelfReviewFragment.CHOSEN_PEERS))
        stateHandle.get<PersonList>(SelfReviewFragment.CHOSEN_PEERS)!!.peersList
    else mutableListOf(
        Person(userId = 1, profileId = 1, username = "Pet owner",
        photoUrl = "https://media-be.chewy.com/wp-content/uploads/shutterstock_492574771.jpg", sbis="125")
    )

    val arePeersSaved = MutableStateFlow(false)

    suspend fun verifyAndSavePeers()
    {
        /*if (parentFragmentEnum == ParentFragmentEnum.VERIFY_PEERS) {
            savePeersOfACurrentUser(currentUserId, chosenPeers.map {
                it.userId
            })
        } else if (parentFragmentEnum == ParentFragmentEnum.SELF_REVIEW) {
            saveMyPeersUseCase(chosenPeers.map {
                it.userId
            })
        }*/
        savePeersOfACurrentUser(currentUserId, chosenPeers.map {
            it.userId
        })

        arePeersSaved.value = true
    }
}