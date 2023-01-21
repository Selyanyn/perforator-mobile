package com.example.perforatormobile.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val id: Int = 0,
    val isDraft: Boolean = false,
    val grades: List<Grade> = emptyList(),
    val isNotEnoughData: Boolean = false
) : Parcelable
