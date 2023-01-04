package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.data.repositories.data_classes.SubordinatesBySelfReviewStatus
import com.example.perforatormobile.domain.entities.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PeersRepository {
    fun getAllCurrentUserPeers(): Flow<List<Person>>

    suspend fun deletePeers(ids: List<Int>)

    suspend fun savePeers(ids: List<Int>)

    fun searchNewPeers(firstName: String): List<Person>

    fun getAllTeammates(id: Int): Flow<List<Person>>

    fun getAllSubordinates(id: Int): SubordinatesBySelfReviewStatus

    fun isUserApproved(id: Int): Boolean

}