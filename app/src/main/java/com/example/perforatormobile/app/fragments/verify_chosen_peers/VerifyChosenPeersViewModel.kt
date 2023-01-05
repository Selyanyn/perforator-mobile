package com.example.perforatormobile.app.fragments.verify_chosen_peers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.data.repositories.data_classes.SubordinatesBySelfReviewStatus
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.usecases.verify_peers.GetAllSubordinatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class VerifyChosenPeersViewModel @Inject constructor(
    private val getAllSubordinatesUseCase: GetAllSubordinatesUseCase
) : ViewModel() {

    val subordinates = flow {
        val data = getAllSubordinatesUseCase()
        emit(data)
    }
}