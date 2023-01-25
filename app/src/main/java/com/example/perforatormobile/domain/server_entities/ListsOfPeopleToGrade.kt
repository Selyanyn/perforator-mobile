package com.example.perforatormobile.domain.server_entities

import android.os.Parcelable
import com.example.perforatormobile.domain.entities.Person
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListsOfPeopleToGrade(
    val peers: List<Person>,
    val manager: Person?,
    val subordinates: List<Person>
) : Parcelable