package com.example.perforatormobile.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Grade(
    val id: Int = 0,
    val category: Category? = null,
    val grade: Int = 0,
    val comment: String? = null
) : Parcelable