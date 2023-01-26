package com.example.perforatormobile.app.fragments.results

import androidx.lifecycle.ViewModel
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.usecases.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    val getCurrentUserUseCase: GetCurrentUserUseCase
): ViewModel() {
    val subordinates = mutableListOf<Person>(Person(10, 10, "Rill", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/1024px-Cat03.jpg", ""))
}