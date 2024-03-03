package com.lloyd.domain.repository


import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.model.DogDetails

interface DogRepository {
    suspend fun getDogBreeds(): DogBreed
    suspend fun getDogDetailsByBreedName(dogBreedName: String): DogDetails
}