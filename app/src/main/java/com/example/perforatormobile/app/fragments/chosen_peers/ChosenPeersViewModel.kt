package com.example.perforatormobile.app.fragments.chosen_peers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.perforatormobile.app.fragments.self_review.SelfReviewFragment
import com.example.perforatormobile.app.fragments.self_review.SelfReviewFragment.Companion.CHOSEN_PEERS
import com.example.perforatormobile.app.fragments.verify_chosen_peers.VerifyChosenPeersFragment
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.PersonList
import com.example.perforatormobile.domain.enums.ParentFragmentEnum
import com.example.perforatormobile.domain.usecases.my_team.GetAllPeersOfASubordinate
import com.example.perforatormobile.domain.usecases.my_team.SaveMyPeersUseCase
import com.example.perforatormobile.domain.usecases.verify_peers.SavePeersOfACurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ChosenPeersViewModel @Inject constructor(
    private val savePeersOfACurrentUser: SavePeersOfACurrentUser,
    private val getAllPeersOfASubordinate: GetAllPeersOfASubordinate,
    private val stateHandle: SavedStateHandle
): ViewModel() {
    val currentUserId = stateHandle.get<Int>(VerifyChosenPeersFragment.SUBORDINATE_ID)!!
    val chosenPeers: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())

    val arePeersSaved = MutableStateFlow(false)

    suspend fun fetchPeers()
    {
        if (stateHandle.contains(CHOSEN_PEERS)) {
            chosenPeers.value = stateHandle.get<PersonList>(CHOSEN_PEERS)!!.peersList
        } else {
            val peers = getAllPeersOfASubordinate(currentUserId).body()
            chosenPeers.value = peers ?: emptyList()
        }
    }

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
        savePeersOfACurrentUser(currentUserId, chosenPeers.value.map {
            it.userId
        })

        arePeersSaved.value = true
    }
}