package com.lloyd.domain.di

import com.lloyd.domain.usecase.GetDogBreedsUseCase
import com.lloyd.domain.usecase.GetDogBreedsUseCaseImpl
import com.lloyd.domain.usecase.GetDogDetailsUseCase
import com.lloyd.domain.usecase.GetDogDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class DomainLayerModule {
        @Binds
        abstract fun bindGetDogBreedsUseCase(
            getDogBreedsUseCaseImpl: GetDogBreedsUseCaseImpl
        ): GetDogBreedsUseCase

        @Binds
        abstract fun bindGetDogDetailsUseCase(
            getDogDetailsUseCaseImpl: GetDogDetailsUseCaseImpl
        ): GetDogDetailsUseCase
    }
}