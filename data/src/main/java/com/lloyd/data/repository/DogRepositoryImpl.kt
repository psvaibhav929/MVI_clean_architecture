package com.lloyd.data.repository

import com.lloyd.common.ErrorFactory
import com.lloyd.common.Result
import com.lloyd.data.mappers.toDogBreed
import com.lloyd.data.mappers.toDogDetails
import com.lloyd.data.services.DogService
import com.lloyd.domain.repository.DogRepository
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.model.DogDetails
import javax.inject.Inject


class DogRepositoryImpl @Inject constructor(
    private val dogApi: DogService
) : DogRepository {

    override suspend fun getDogBreeds(): Result<DogBreed> {
        return try {
            val response = dogApi.getAllBreeds()
            if (response.isSuccessful) {
                val dogBreedDto = response.body()
                if (dogBreedDto != null) {
                    Result.Success(dogBreedDto.toDogBreed())
                } else {
                    Result.Error("Response body is null")
                }
            } else {
                Result.Error(ErrorFactory.getErrorMessageFromCode(response.code()))
            }
        } catch (e: Exception) {
            Result.Error(ErrorFactory.getErrorMessage(e))
        }
    }

    override suspend fun getDogDetailsByBreedName(dogBreedName: String): Result<DogDetails> {
        return try {
            val response = dogApi.getDogDetailsByBreedName(dogBreedName)
            if (response.isSuccessful) {
                val dogDetailsDto = response.body()
                if (dogDetailsDto != null) {
                    Result.Success(dogDetailsDto.toDogDetails())
                } else {
                    Result.Error("Error getting dog breeds: ${response.message()}")
                }
            } else {
                Result.Error(ErrorFactory.getErrorMessageFromCode(response.code()))
            }
        } catch (e: Exception) {
            Result.Error(ErrorFactory.getErrorMessage(e))
        }
    }
}
