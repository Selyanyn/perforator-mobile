package com.example.perforatormobile.domain.server_entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginUser(
    val id: String,
    val password: String
) : Parcelable