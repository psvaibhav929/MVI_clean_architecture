package com.mvi.domain.repository


import com.mvi.domain.model.DogBreed
import com.mvi.domain.model.DogDetails
import com.mvi.domain.result.ApiResult

interface DogRepository {
    suspend fun getDogBreeds(): ApiResult<DogBreed>
    suspend fun getDogDetailsByBreedName(dogBreedName: String):  ApiResult<DogDetails>
}