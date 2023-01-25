package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.domain.entities.Person
import com.example.perforatormobile.domain.entities.User
import com.example.perforatormobile.domain.server_entities.LoginRequestData
import com.example.perforatormobile.domain.server_entities.LoginResponseData
import com.example.perforatormobile.domain.server_entities.LoginUser
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface UsersRepository {
    //Возвращаем токены?
    @Multipart
    @POST("perforator/registration/")
    suspend fun registerUser(@Part("name") userName: RequestBody,
                             @Part("password") password: RequestBody,
                             @Part("phone") phone: RequestBody,
                             @Part photo: MultipartBody.Part,
                             @Part("sbis") sbis: RequestBody)

    @POST("/perforator/api/login")
    suspend fun loginUser(@Body user: LoginRequestData): Response<LoginResponseData>

    suspend fun updateUserInfo(person: Person)

    suspend fun logOut()

    suspend fun getCurrentUser(): User
}