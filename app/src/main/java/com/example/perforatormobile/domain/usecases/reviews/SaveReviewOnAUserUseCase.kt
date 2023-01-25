package com.example.perforatormobile.domain.usecases.reviews

import com.example.perforatormobile.domain.entities.Review
import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import javax.inject.Inject

class SaveReviewOnAUserUseCase @Inject constructor(
    private val reviewsRepository: ReviewsRepository
) {
    suspend operator fun invoke(review: Review) = reviewsRepository.saveReview(review)
}