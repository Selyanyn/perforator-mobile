package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Person
import kotlinx.coroutines.flow.Flow

interface PeersRepository {
    fun getAllCurrentUserPeers(): Flow<List<Person>>

    suspend fun deletePeers(ids: List<Int>)

    suspend fun savePeers(ids: List<Int>)

    fun searchPeers(firstName: String): List<Person>

    fun getAllTeammates(id: Int): Flow<List<Person>>

    fun getAllSubordinates(id: Int): Flow<List<Person>>

    fun isUserApproved(id: Int): Boolean

}