package com.example.perforatormobile.domain.repository_interfaces

import com.example.perforatormobile.data.repositories.data_classes.SubordinatesBySelfReviewStatus
import com.example.perforatormobile.domain.entities.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import retrofit2.http.*

interface PeersRepository {
    @GET("perforator/peers/my/")
    suspend fun getAllCurrentUserPeers(): Response<List<Person>>

    suspend fun deletePeers(ids: List<Int>)

    @POST("perforator/peers/save/")
    suspend fun savePeers(@Body ids: List<Int>)

    fun searchNewPeers(firstName: String): List<Person>

    @GET("perforator/peers/all/")
    suspend fun getAllPotentialPeers(): Response<List<Person>>

    fun getAllTeammates(id: Int): Flow<List<Person>>

    @GET("perforator/team")
    suspend fun getAllSubordinates(): Response<List<Person>>

    @GET("perforator/manager")
    suspend fun getMyManager(): Response<Person>

    fun isUserApproved(id: Int): Boolean

    @GET("perforator/peers/id")
    suspend fun getAllPeersOfASubordinate(@Query("id") id: Int): Response<List<Person>?>

    @POST("perforator/peers/save/user")
    suspend fun savePeersOfACurrentUser(
        @Query("id") id: Int,
        @Body ids: List<Int>
    )

    @POST("perforator/peers/approve")
    suspend fun approveSubordinate(
        @Query("id") id: Int
    )
}