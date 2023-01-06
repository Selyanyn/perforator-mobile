package com.example.perforatormobile.app.fragments.choose_new_peers

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.R
import com.example.perforatormobile.app.fragments.choose_new_peers.ChoosePeersFragment.Companion.NAVIGATE_ACTION
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.usecases.self_review.GetSelfReviewFormUseCase
import com.example.perforatormobile.domain.usecases.self_review.SearchNewPeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChoosePeersViewModel @Inject constructor(
    private val searchNewPeersUseCase: SearchNewPeersUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val _searchPeersText = MutableStateFlow("")
    val searchedPeers = MutableStateFlow(searchNewPeersUseCase("x"))
    val chosenPeers: MutableList<Person> = ArrayList()
    val navigateAction: Int = if (stateHandle.contains(NAVIGATE_ACTION))
        stateHandle.get<Int>(NAVIGATE_ACTION)!! else R.id.action_navigation_choose_peers_to_self_review

    fun doOnSearchPeersTextChanged(firstName: String) {
        if (firstName == _searchPeersText.value) return
        viewModelScope.launch {
            searchedPeers.value = searchNewPeersUseCase(firstName)
            _searchPeersText.value = firstName
        }
    }

    fun removeAndSavePeerAtPosition(position: Int) {
        chosenPeers.add(searchedPeers.value[position])
        searchedPeers.value.removeAt(position)
    }
}