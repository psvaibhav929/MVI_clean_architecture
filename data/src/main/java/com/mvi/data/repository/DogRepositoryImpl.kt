package com.mvi.data.repository

import com.mvi.common.ApiResult
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

    override suspend fun getDogBreeds(): ApiResult<DogBreed> {
        return try {
            val response = dogApi.getAllBreeds()
            if (response.isSuccessful) {
                val dogBreedDto = response.body()
                if (dogBreedDto != null) {
                    ApiResult.Success(dogMappers.toDogBreed(dogBreedDto))
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
                    ApiResult.Success(dogMappers.toDogDetails(dogDetailsDto))
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
