package com.example.perforatormobile.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("is_draft")
    val isDraft: Boolean = false,
    @SerializedName("grades")
    val grades: List<Grade> = emptyList(),
    @SerializedName("is_not_enough_data")
    val isNotEnoughData: Boolean = false,
    @SerializedName("evaluated_person_id")
    val evaluatedPersonId: Int? = null
) : Parcelable
