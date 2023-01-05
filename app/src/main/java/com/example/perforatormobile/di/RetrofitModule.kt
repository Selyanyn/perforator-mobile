package com.example.perforatormobile.di

import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun providesBaseUrl() : String = "https://perforator.com/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL : String) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    //@Provides
    //@Singleton
    //fun provideUserRepository(retrofit : Retrofit) : UsersRepository = retrofit.create(UsersRepository::class.java)
}