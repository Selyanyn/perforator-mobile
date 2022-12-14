package com.example.perforatormobile.data.repositories

import com.example.perforatormobile.domain.entities.Category
import com.example.perforatormobile.domain.entities.Grade
import com.example.perforatormobile.domain.entities.Review
import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewsRepositoryImplementation @Inject constructor(): ReviewsRepository {
    override fun getSelfReview(): Review {
        return Review(1, true,
            listOf(Grade(1, Category(1, "cat question", "cat_desc"), 5, "great!"),
                Grade(2, Category(2, "dog question", "dog_desc"), 3, "ok...")),
        false)
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