package com.example.perforatormobile.data

import com.example.perforatormobile.domain.entities.Category
import com.example.perforatormobile.domain.entities.Review
import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import kotlinx.coroutines.flow.Flow

class ReviewsRepositoryImpl(): ReviewsRepository {
    override fun getSelfReview(): Review {
        TODO("Not yet implemented")
    }

    override suspend fun editSelfReview(review: Review) {
        TODO("Not yet implemented")
    }

    override fun isDraft(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEmptyReviewForm(): Flow<List<Category>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveReview(review: Review) {
        TODO("Not yet implemented")
    }
}