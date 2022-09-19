package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Category
import com.example.perforatormobile.domain.entities.Review
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ReviewsRepository {

    fun getSelfReview(): Review

    suspend fun editSelfReview(review: Review)

    fun isDraft(id: Int): Boolean

    fun getEmptyReviewForm(): Flow<List<Category>>

    suspend fun saveReview(review: Review)
}