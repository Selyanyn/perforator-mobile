package com.example.perforatormobile.app.fragments.peers_review

import androidx.lifecycle.ViewModel
import com.example.perforatormobile.domain.entities.Person
import javax.inject.Inject

class PeersReviewViewModel @Inject constructor(): ViewModel() {
    val peersToReview = mutableListOf<Person>(Person(10, 10, "Rill", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/1024px-Cat03.jpg", ""))
}