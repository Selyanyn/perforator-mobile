package com.example.perforatormobile.domain.usecases.reviews

import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import javax.inject.Inject

class GetReviewFormsStubsCase @Inject constructor(
    private val reviewsRepository: ReviewsRepository
) {
    suspend operator fun invoke() = reviewsRepository.getEmptyReviewFormStubs()
}