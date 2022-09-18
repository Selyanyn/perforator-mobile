package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Review

interface ReviewsRepository {

    fun getSelfReview(): Review

    suspend fun editSelfReview(review: Review)

    fun isDraft(id: Int): Boolean
}