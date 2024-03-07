package com.mvi.domain.repository


import com.mvi.common.ApiResult
import com.mvi.domain.model.DogBreed
import com.mvi.domain.model.DogDetails

interface DogRepository {
    suspend fun getDogBreeds():  ApiResult<DogBreed>
    suspend fun getDogDetailsByBreedName(dogBreedName: String):  ApiResult<DogDetails>
}