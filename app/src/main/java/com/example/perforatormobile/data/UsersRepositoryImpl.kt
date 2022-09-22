package com.example.perforatormobile.data

import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.repository_interfaces.UsersRepository

class UsersRepositoryImpl: UsersRepository {
    override suspend fun registerUser(userName: String, password: String, phone: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(userName: String, password: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserInfo(person: Person) {
        TODO("Not yet implemented")
    }

    override suspend fun logOut() {
        TODO("Not yet implemented")
    }
}