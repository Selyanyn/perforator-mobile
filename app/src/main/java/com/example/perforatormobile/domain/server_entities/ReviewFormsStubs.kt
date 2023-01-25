package com.example.perforatormobile.domain.server_entities

import android.os.Parcelable
import com.example.perforatormobile.domain.entities.Category
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewFormsStubs(
    @SerializedName("manager_review_categories")
    val managerReviewCategories: List<Category> = emptyList(),
    @SerializedName("team_review_categories")
    val teamReviewCategories: List<Category> = emptyList(),
    @SerializedName("peers_review_categories")
    val peersReviewCategories: List<Category> = emptyList()
) : Parcelable