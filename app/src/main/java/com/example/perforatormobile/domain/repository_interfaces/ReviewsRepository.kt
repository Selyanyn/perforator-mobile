package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Category
import com.example.perforatormobile.domain.entities.Review
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

interface ReviewsRepository {

    @GET("perforator/self-review/")
    suspend fun getSelfReview(): Response<Review>

    suspend fun editSelfReview(review: Review)

    fun isDraft(id: Int): Boolean

    fun getEmptyReviewForm(): Flow<List<Category>>

    suspend fun saveReview(review: Review)
}