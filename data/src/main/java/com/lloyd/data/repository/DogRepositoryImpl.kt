package com.lloyd.data.repository

import com.lloyd.common.ErrorFactory
import com.lloyd.common.ApiResult
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

    override suspend fun getDogBreeds(): ApiResult<DogBreed> {
        return try {
            val response = dogApi.getAllBreeds()
            if (response.isSuccessful) {
                val dogBreedDto = response.body()
                if (dogBreedDto != null) {
                    ApiResult.Success(dogBreedDto.toDogBreed())
                } else {
                    ApiResult.Error("Response body is null")
                }
            } else {
                ApiResult.Error(ErrorFactory.getErrorMessageFromCode(response.code()))
            }
        } catch (e: Exception) {
            ApiResult.Error(ErrorFactory.getErrorMessage(e))
        }
    }



    override suspend fun getDogDetailsByBreedName(dogBreedName: String): ApiResult<DogDetails> {
        return try {
            val response = dogApi.getDogDetailsByBreedName(dogBreedName)
            if (response.isSuccessful) {
                val dogDetailsDto = response.body()
                if (dogDetailsDto != null) {
                    ApiResult.Success(dogDetailsDto.toDogDetails())
                } else {
                    ApiResult.Error("Error getting dog breeds: ${response.message()}")
                }
            } else {
                ApiResult.Error(ErrorFactory.getErrorMessageFromCode(response.code()))
            }
        } catch (e: Exception) {
            ApiResult.Error(ErrorFactory.getErrorMessage(e))
        }
    }
}
