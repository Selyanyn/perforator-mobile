package com.example.perforatormobile.app.fragments.verify_chosen_peers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.usecases.verify_peers.GetAllSubordinatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyChosenPeersViewModel @Inject constructor(
    private val getAllSubordinatesUseCase: GetAllSubordinatesUseCase
) : ViewModel() {

    val subordinates = getAllSubordinatesUseCase()

    val subordinatesNotFinishedSelfReview = MutableStateFlow(subordinates.subordinatesNotFinishedSelfReview)
    val notVerifiedSubordinates = MutableStateFlow(subordinates.notVerifiedSubordinates)
    val verifiedSubordinates = MutableStateFlow(subordinates.verifiedSubordinates)

}