package com.example.perforatormobile.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    @SerializedName("user_id")
    val userId: Int = 0,
    @SerializedName("profile_id")
    val profileId: Int = 0,
    @SerializedName("username")
    val username: String = "",
    @SerializedName("photo")
    val photoUrl: String = "",
    @SerializedName("sbis")
    val sbis: String = "",
    @SerializedName("approve")
    val approve: Boolean = false
) : Parcelable
