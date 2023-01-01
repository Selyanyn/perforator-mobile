package com.example.perforatormobile.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val userId: Int = 0,
    val profileId: Int = 0,
    val username: String = "",
    val photoUrl: String = "",
    val sbis: String = "",
) : Parcelable
