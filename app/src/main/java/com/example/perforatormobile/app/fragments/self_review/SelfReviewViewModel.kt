package com.example.perforatormobile.app.fragments.self_review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.perforatormobile.domain.entities.Grade
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.Review
import com.example.perforatormobile.domain.usecases.my_team.SaveMyPeersUseCase
import com.example.perforatormobile.domain.usecases.reviews.EditSelfReviewUseCase
import com.example.perforatormobile.domain.usecases.self_review.GetSelfReviewFormUseCase
import com.example.perforatormobile.domain.usecases.self_review.GetAllCurrentUserPeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.forEach
import javax.inject.Inject

@HiltViewModel
class SelfReviewViewModel @Inject constructor(
    private val getSelfReviewFormUseCase: GetSelfReviewFormUseCase,
    private val getAllCurrentUserPeersUseCase: GetAllCurrentUserPeersUseCase,
    private val editSelfReviewUseCase: EditSelfReviewUseCase,
    private val saveMyPeersUseCase: SaveMyPeersUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {

    val selfReview : MutableStateFlow<Review?> = MutableStateFlow(null)
    val chosenPeers: MutableStateFlow<MutableList<Person>?> = MutableStateFlow(mutableListOf())
    val isSelfReviewSaved = MutableStateFlow(false)
    val grades = mutableListOf<Grade>()

    suspend fun getSelfReview()
    {
        selfReview.value = getSelfReviewFormUseCase().body()
        grades.addAll(selfReview.value!!.grades)
    }

    suspend fun fetchAllChosenPeers()
    {
        chosenPeers.value = getAllCurrentUserPeersUseCase().body()?.toMutableList()
    }

    suspend fun saveReview(isDraft: Boolean)
    {
        val selfReview = selfReview.value!!.copy(
            isDraft = isDraft,
            grades = grades
        )
        editSelfReviewUseCase(selfReview)
        saveMyPeersUseCase(chosenPeers.value!!.map {
            it.userId
        })
        isSelfReviewSaved.value = true
    }

    fun updateGrades(position: Int, comment: String)
    {
        grades[position] = grades[position].copy(comment = comment)
    }
}