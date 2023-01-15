package com.example.perforatormobile.di

import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import com.google.gson.GsonBuilder
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
    fun providesBaseUrl() : String = "http://10.0.2.2:8000/"
    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL : String) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()))
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideUserRepository(retrofit : Retrofit) : UsersRepository = retrofit.create(UsersRepository::class.java)
}