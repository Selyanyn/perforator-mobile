package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Person

interface UserRepository {
    //Возвращаем токены?
    suspend fun registerUser(email: String, password: String, phone: String): String

    suspend fun loginUser(email: String, password: String): String

    suspend fun updateUserInfo(person: Person)

    suspend fun logOut()
}