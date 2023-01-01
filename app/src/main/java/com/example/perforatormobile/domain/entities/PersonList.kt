package com.example.perforatormobile.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonList(
    public val peersList: MutableList<Person> = ArrayList()
): Parcelable
