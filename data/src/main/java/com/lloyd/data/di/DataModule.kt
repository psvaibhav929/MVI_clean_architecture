package com.lloyd.data.di

import com.lloyd.data.repository.DogRepositoryImpl
import com.lloyd.domain.repository.DogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {
    @Module
    @InstallIn(ViewModelComponent::class)
    abstract class DataLayerModule {
        @Binds
        abstract fun bindDogRepository(
            dogRepositoryImpl: DogRepositoryImpl
        ): DogRepository
    }
}