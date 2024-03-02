package com.lloyd.data.di

import com.lloyd.common.Constants
import com.lloyd.data.remote.DogApi
import com.lloyd.data.repository.DogRepositoryImpl
import com.lloyd.domain.repository.DogRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDogApi(): DogApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class DataLayerModule {
        @Binds
        abstract fun bindDogRepository(
            dogRepositoryImpl: DogRepositoryImpl
        ): DogRepository
    }

}