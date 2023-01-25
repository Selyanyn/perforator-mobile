package com.example.perforatormobile.app.fragments.grade_questions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.perforatormobile.app.fragments.peers_review.PeersReviewFragment.Companion.CATEGORIES_ID
import com.example.perforatormobile.app.fragments.peers_review.PeersReviewFragment.Companion.PEER_ID
import com.example.perforatormobile.domain.entities.Category
import com.example.perforatormobile.domain.entities.Grade
import com.example.perforatormobile.domain.entities.Review
import com.example.perforatormobile.domain.usecases.reviews.SaveReviewOnAUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class GradeQuestionsViewModel @Inject constructor(
    private val saveReviewOnAUserUseCase: SaveReviewOnAUserUseCase,
    stateHandle: SavedStateHandle
): ViewModel() {
    val peerId = if (stateHandle.contains(PEER_ID))
        stateHandle.get<Int>(PEER_ID)!!
        else throw Exception("No peer Id was trasmited!")

    val categories = if (stateHandle.contains(CATEGORIES_ID))
        stateHandle.get<List<Category>>(CATEGORIES_ID)
        else throw Exception("No categories ware trasmited!")

    val grades: MutableList<Grade>
    init {
        val result = mutableListOf<Grade>()
        for (i in 0 until categories!!.size) {
            result.add(Grade(i, categories[i], 0, ""))
        }
        grades = result
    }

    val isReviewSaved = MutableStateFlow(false)

    suspend fun saveReview()
    {
        val review = Review(
            0,
            false,
            grades,
            false,
            peerId
        )
        saveReviewOnAUserUseCase(review)
        isReviewSaved.value = true
    }

    fun updateComment(position: Int, comment: String)
    {
        grades[position] = grades[position].copy(comment = comment)
    }

    fun updateGrade(position: Int, grade: Int)
    {
        grades[position] = grades[position].copy(grade = grade)
    }
}