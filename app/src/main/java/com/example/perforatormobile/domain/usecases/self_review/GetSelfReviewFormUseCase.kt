package com.example.perforatormobile.domain.usecases.self_review

import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import javax.inject.Inject

class GetSelfReviewFormUseCase @Inject constructor(
    private val reviewsRepository: ReviewsRepository
) {
    suspend operator fun invoke() = reviewsRepository.getSelfReview()
}