package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersRepository {
    //Возвращаем токены?
    @GET("registration/")
    suspend fun registerUser(@Query("username") userName: String,
                             @Query("password") password: String,
                             @Query("phone") phone: String): Response<String>

    @GET("api/token/")
    suspend fun loginUser(@Query("username") userName: String,
                          @Query("password") password: String): Response<String>

    suspend fun updateUserInfo(person: Person)

    suspend fun logOut()

    fun getCurrentUser(): User
}