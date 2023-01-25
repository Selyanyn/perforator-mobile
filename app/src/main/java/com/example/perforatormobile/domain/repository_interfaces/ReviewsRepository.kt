package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Category
import com.example.perforatormobile.domain.entities.Review
import com.example.perforatormobile.domain.server_entities.ReviewFormsStubs
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

interface ReviewsRepository {

    @GET("perforator/self-review/")
    suspend fun getSelfReview(): Response<Review>

    @POST("perforator/self-review/save/")
    suspend fun editSelfReview(@Body review: Review)

    fun isDraft(id: Int): Boolean

    @GET("perforator/review/form/")
    suspend fun getEmptyReviewFormStubs(): Response<ReviewFormsStubs>

    @POST("perforator/review/save/")
    suspend fun saveReview(@Body review: Review)
}