package com.example.perforatormobile.data.repositories

import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.User
import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import retrofit2.Response
import javax.inject.Inject

class UsersRepositoryImplementation @Inject constructor(): UsersRepository {
    override suspend fun registerUser(
        userName: String,
        password: String,
        phone: String
    ): Response<String> {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(userName: String, password: String): Response<String> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserInfo(person: Person) {
        TODO("Not yet implemented")
    }

    override suspend fun logOut() {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): User {
        return User(0, 0, "")
    }
}