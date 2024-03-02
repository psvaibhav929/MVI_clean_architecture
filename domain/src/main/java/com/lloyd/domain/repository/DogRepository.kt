package com.lloyd.domain.repository

import com.lloyd.domain.repository.dto.DogBreedDto
import com.lloyd.domain.repository.dto.DogDetailsDto

interface DogRepository {
    suspend fun getDogBreeds(): DogBreedDto
    suspend fun getDogDetailsByBreedName(dogBreedName: String): DogDetailsDto
}