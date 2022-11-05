package com.example.perforatormobile.app.fragments.self_review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.usecases.self_review.GetSelfReviewFormUseCase
import com.example.perforatormobile.domain.usecases.self_review.SearchPeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelfReviewViewModel @Inject constructor(
    private val getSelfReviewFormUseCase: GetSelfReviewFormUseCase,
    private val searchPeersUseCase: SearchPeersUseCase
) : ViewModel() {

    val selfReview = getSelfReviewFormUseCase()
    val chosenPeers = mutableListOf(Person(userId = 1, profileId = 1, username = "Pet owner",
                    photoUrl = "https://media-be.chewy.com/wp-content/uploads/shutterstock_492574771.jpg", sbis="125"))
    //private val _peersSearch = MutableStateFlow<String>("")
    val searchedPeers = mutableListOf<Person>()

    fun doOnSearchPeersTextChanged(firstName: String) {
        viewModelScope.launch {
            searchedPeers.clear()
            searchedPeers.addAll(0, searchPeersUseCase(firstName))
        }
    }
}