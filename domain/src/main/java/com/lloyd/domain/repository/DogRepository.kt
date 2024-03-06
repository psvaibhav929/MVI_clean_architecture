package com.lloyd.domain.repository


import com.lloyd.common.ApiResult
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.model.DogDetails

interface DogRepository {
    suspend fun getDogBreeds():  ApiResult<DogBreed>
    suspend fun getDogDetailsByBreedName(dogBreedName: String):  ApiResult<DogDetails>
}