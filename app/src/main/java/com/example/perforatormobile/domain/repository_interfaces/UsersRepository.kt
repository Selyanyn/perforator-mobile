package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Person

interface UsersRepository {
    //Возвращаем токены?
    suspend fun registerUser(userName: String, password: String, phone: String): String

    suspend fun loginUser(userName: String, password: String): String

    suspend fun updateUserInfo(person: Person)

    suspend fun logOut()
}