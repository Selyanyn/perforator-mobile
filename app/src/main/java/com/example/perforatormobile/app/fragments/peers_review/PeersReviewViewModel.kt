package com.example.perforatormobile.app.fragments.peers_review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.Review
import com.example.perforatormobile.domain.server_entities.ListsOfPeopleToGrade
import com.example.perforatormobile.domain.server_entities.ReviewFormsStubs
import com.example.perforatormobile.domain.usecases.my_team.GetMyManagerUseCase
import com.example.perforatormobile.domain.usecases.reviews.GetReviewFormsStubsCase
import com.example.perforatormobile.domain.usecases.self_review.GetAllCurrentUserPeersUseCase
import com.example.perforatormobile.domain.usecases.self_review.GetSelfReviewFormUseCase
import com.example.perforatormobile.domain.usecases.verify_peers.GetAllSubordinatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PeersReviewViewModel @Inject constructor(
    private val getEmptyReviewFormStubs: GetReviewFormsStubsCase,
    private val getAllCurrentUserPeers: GetAllCurrentUserPeersUseCase,
    private val getMyManagerUseCase: GetMyManagerUseCase,
    private val getAllSubordinatesUseCase: GetAllSubordinatesUseCase
): ViewModel() {
    val listsOfPeopleToGrade = MutableStateFlow(ListsOfPeopleToGrade(
        emptyList(), null, emptyList()
    ))
    val reviewFormStubs: MutableStateFlow<ReviewFormsStubs> = MutableStateFlow(ReviewFormsStubs())

    suspend fun fetchQuestionsStubs()
    {
        val value = getEmptyReviewFormStubs().body()
        if (value !== null) {
            reviewFormStubs.value = value
        }
        val o = 0
    }

    suspend fun fetchPeopleToReview()
    {
        val peersToReview = getAllCurrentUserPeers().body()!!
        val managerToReview = getMyManagerUseCase().body()
        val subordinatesToReview = getAllSubordinatesUseCase().body()!!
        listsOfPeopleToGrade.value = ListsOfPeopleToGrade(
            peersToReview,
            managerToReview,
            subordinatesToReview
        )
    }

}