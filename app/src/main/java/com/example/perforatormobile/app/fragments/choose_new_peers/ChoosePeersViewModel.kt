package com.example.perforatormobile.app.fragments.choose_new_peers

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.R
import com.example.perforatormobile.app.fragments.choose_new_peers.ChoosePeersFragment.Companion.NAVIGATE_ACTION
import com.example.perforatormobile.app.fragments.chosen_peers.ChosenPeersFragment
import com.example.perforatormobile.app.fragments.verify_chosen_peers.VerifyChosenPeersFragment
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.enums.ParentFragmentEnum
import com.example.perforatormobile.domain.usecases.self_review.GetSelfReviewFormUseCase
import com.example.perforatormobile.domain.usecases.self_review.SearchNewPeersUseCase
import com.example.perforatormobile.domain.usecases.verify_peers.GetAllPotentialPeers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChoosePeersViewModel @Inject constructor(
    private val getAllPotentialPeers: GetAllPotentialPeers,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val _searchPeersText = MutableStateFlow("")
    val searchedPeers = MutableStateFlow(listOf<Person>())
    val chosenPeers = mutableListOf<Person>()
    private val parentFragmentEnum = ParentFragmentEnum.values()[
            stateHandle.get<Int>(ChosenPeersFragment.PARENT_FRAGMENT)!!
    ]
    val navigateAction: Int = if (parentFragmentEnum == ParentFragmentEnum.VERIFY_PEERS)
        R.id.action_navigation_choose_peers_to_chosen_peers
        else R.id.action_navigation_choose_peers_to_self_review
    val currentUserId = if (parentFragmentEnum == ParentFragmentEnum.VERIFY_PEERS)
        stateHandle.get<Int>(VerifyChosenPeersFragment.SUBORDINATE_ID)!!
        else 0
    lateinit var fetchedPeers: List<Person>

    fun doOnSearchPeersTextChanged(firstName: String) {
        searchedPeers.value = fetchedPeers.filter {
            it.username.startsWith(firstName)
        }.take(9)
    }

    fun removeAndSavePeerAtPosition(position: Int) {
        chosenPeers.add(searchedPeers.value[position])
        searchedPeers.value = searchedPeers.value.filterIndexed { index, _ ->
            index != position
        }
    }

    suspend fun fetchPeers()
    {
        fetchedPeers = getAllPotentialPeers().body()!!
        doOnSearchPeersTextChanged("")
    }
}