package com.example.perforatormobile.di

import com.example.perforatormobile.data.repositories.PeersRepositoryImplementation
import com.example.perforatormobile.data.repositories.ReviewsRepositoryImplementation
import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesReviewsRepository(reviewsRepositoryImplementation: ReviewsRepositoryImplementation): ReviewsRepository
    @Binds
    abstract fun providesPeersRepository(peersRepositoryImplementation: PeersRepositoryImplementation): PeersRepository
}