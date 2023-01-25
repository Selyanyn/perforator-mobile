package com.example.perforatormobile.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "generic_name",
    @SerializedName("description")
    val description: String = "generic_description",
    @SerializedName("preview_description")
    val preview_description: String = "generic_preview_description"
) : Parcelable
