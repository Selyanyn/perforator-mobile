package com.example.perforatormobile.data.repositories

import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import kotlinx.coroutines.flow.Flow

class PeersRepositoryImplementation: PeersRepository {
    override fun getAllCurrentUserPeers(): Flow<List<Person>> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePeers(ids: List<Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun savePeers(ids: List<Int>) {
        TODO("Not yet implemented")
    }

    override fun searchPeers(firstName: String): Flow<List<Person>> {
        TODO("Not yet implemented")
    }

    override fun getAllTeammates(id: Int): Flow<List<Person>> {
        TODO("Not yet implemented")
    }

    override fun getAllSubordinates(id: Int): Flow<List<Person>> {
        TODO("Not yet implemented")
    }

    override fun isUserApproved(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}