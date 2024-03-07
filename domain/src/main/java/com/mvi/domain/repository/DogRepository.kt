package com.mvi.domain.repository


import com.mvi.domain.model.DogBreed
import com.mvi.domain.model.DogDetails

interface DogRepository {
    suspend fun getDogBreeds():  Result<DogBreed>
    suspend fun getDogDetailsByBreedName(dogBreedName: String):  Result<DogDetails>
}