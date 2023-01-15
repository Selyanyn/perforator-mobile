package com.example.perforatormobile.domain.server_entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequestData(
    val user: LoginUser
) : Parcelable