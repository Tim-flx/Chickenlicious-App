package com.dicoding.chicken.di

import com.dicoding.chicken.data.ChickenRepository
import com.dicoding.chicken.data.ChickenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideChickenRepository(chickenRepositoryImpl: ChickenRepositoryImpl) : ChickenRepository
}