package com.mvi.data.repository

import com.mvi.data.mappers.DogMappers
import com.mvi.data.network.ErrorFactory
import com.mvi.data.services.DogService
import com.mvi.domain.model.DogBreed
import com.mvi.domain.model.DogDetails
import com.mvi.domain.repository.DogRepository
import javax.inject.Inject


class DogRepositoryImpl @Inject constructor(
    private val dogApi: DogService,
    private val dogMappers: DogMappers
) : DogRepository {

    override suspend fun getDogBreeds(): Result<DogBreed> {
        return try {
            val response = dogApi.getAllBreeds()
            if (response.isSuccessful) {
                val dogBreedDto = response.body()
                if (dogBreedDto != null) {
                    Result.success(dogMappers.toDogBreed(dogBreedDto))
                } else {
                    Result.failure(Throwable("Response body is null"))
                }
            } else {
                Result.failure(Throwable(ErrorFactory.getErrorMessageFromCode(response.code())))
            }
        } catch (e: Exception) {
            Result.failure(Throwable(ErrorFactory.getErrorMessage(e)))
        }
    }


    override suspend fun getDogDetailsByBreedName(dogBreedName: String): Result<DogDetails> {
        return try {
            val response = dogApi.getDogDetailsByBreedName(dogBreedName)
            if (response.isSuccessful) {
                val dogDetailsDto = response.body()
                if (dogDetailsDto != null) {
                    Result.success(dogMappers.toDogDetails(dogDetailsDto))
                } else {
                    Result.failure(Throwable("Error getting dog breeds: ${response.message()}"))
                }
            } else {
                Result.failure(Throwable(ErrorFactory.getErrorMessageFromCode(response.code())))
            }
        } catch (e: Exception) {
            Result.failure(Throwable(ErrorFactory.getErrorMessage(e)))
        }
    }
}
