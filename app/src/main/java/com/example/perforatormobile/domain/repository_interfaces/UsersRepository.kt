package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.User
import com.example.perforatormobile.domain.server_entities.LoginRequestData
import com.example.perforatormobile.domain.server_entities.LoginResponseData
import com.example.perforatormobile.domain.server_entities.LoginUser
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UsersRepository {
    //Возвращаем токены?
    @Multipart
    @POST("perforator/registration/")
    suspend fun registerUser(@Query("name") userName: String,
                             @Query("password") password: String,
                             @Query("phone") phone: String,
                             @Part photo: MultipartBody.Part,
                            @Query("sbis") sbis: String = ""): Response<String>

    @POST("/perforator/api/login")
    suspend fun loginUser(@Body user: LoginRequestData): Response<LoginResponseData>

    suspend fun updateUserInfo(person: Person)

    suspend fun logOut()

    suspend fun getCurrentUser(): User
}