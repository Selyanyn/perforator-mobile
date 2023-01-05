package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.User
import retrofit2.Response

interface UsersRepository {
    //Возвращаем токены?
    suspend fun registerUser(userName: String,
                             password: String,
                             phone: String): Response<String>

    suspend fun loginUser(userName: String,
                          password: String): Response<String>

    suspend fun updateUserInfo(person: Person)

    suspend fun logOut()

    suspend fun getCurrentUser(): User
}