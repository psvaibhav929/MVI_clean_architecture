package com.mvi.data.di

import com.mvi.data.repository.DogRepositoryImpl
import com.mvi.domain.repository.DogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindDogRepository(
        dogRepositoryImpl: DogRepositoryImpl
    ): DogRepository
}