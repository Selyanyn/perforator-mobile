package com.example.perforatormobile.di

import com.example.perforatormobile.data.repositories.ReviewsRepositoryImplementation
import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesReviewsRepository(reviewsRepositoryImplementation: ReviewsRepositoryImplementation): ReviewsRepository
}