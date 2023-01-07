package com.example.perforatormobile.app.fragments.grade_questions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.perforatormobile.app.fragments.peers_review.PeersReviewFragment.Companion.PEER_ID
import com.example.perforatormobile.domain.entities.Category
import com.example.perforatormobile.domain.entities.Grade
import javax.inject.Inject

class GradeQuestionsViewModel @Inject constructor(
    stateHandle: SavedStateHandle
): ViewModel() {
    val peerId = if (stateHandle.contains(PEER_ID)) stateHandle.get<Int>(PEER_ID)!! else throw Exception("No peer Id was trasmited!")
    val grades = mutableListOf<Grade>(Grade(1, Category(1, "XYZ", "type XYZ"), 3, "Uwu"),
        Grade(2, Category(2, "Synchro", "type Synchro"), 0))
}