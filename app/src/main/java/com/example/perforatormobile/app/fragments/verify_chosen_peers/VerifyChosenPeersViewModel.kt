package com.example.perforatormobile.app.fragments.verify_chosen_peers

import androidx.lifecycle.ViewModel
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.usecases.verify_peers.GetAllSubordinatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class VerifyChosenPeersViewModel @Inject constructor(
    private val getAllSubordinatesUseCase: GetAllSubordinatesUseCase
) : ViewModel() {
    val approvedSubordinates: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    val unapprovedSubordinates: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    val notFinishedSubordinates: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())

    suspend fun fetchSubordinates()
    {
        val subordinates = getAllSubordinatesUseCase().body()!!
        approvedSubordinates.value = subordinates.filter {
            it.approve
        }
        unapprovedSubordinates.value = subordinates.filter {
            !it.approve
        }
        notFinishedSubordinates.value = emptyList()
    }
}