package com.example.perforatormobile.data.repositories

import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeersRepositoryImplementation @Inject constructor(): PeersRepository {
    override fun getAllCurrentUserPeers(): Flow<List<Person>> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePeers(ids: List<Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun savePeers(ids: List<Int>) {
        TODO("Not yet implemented")
    }

    override fun searchNewPeers(firstName: String): List<Person> {
        return listOf(Person(4, 4, "Sasha", "https://media-be.chewy.com/wp-content/uploads/shutterstock_492574771.jpg", "1"),
            Person(5, 5, "Xora", "https://media-be.chewy.com/wp-content/uploads/shutterstock_492574771.jpg", "1"))
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