package com.example.perforatormobile.di

import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun providesBaseUrl() : String = "http://10.0.2.2:8000/"
    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL : String) : Retrofit {
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(AuthInterceptor())
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(retrofit : Retrofit) : UsersRepository = retrofit.create(UsersRepository::class.java)

    @Provides
    @Singleton
    fun provideReviewsRepository(retrofit: Retrofit): ReviewsRepository = retrofit.create(ReviewsRepository::class.java)

    @Provides
    @Singleton
    fun providePeersRepository(retrofit: Retrofit): PeersRepository = retrofit.create(PeersRepository::class.java)
}