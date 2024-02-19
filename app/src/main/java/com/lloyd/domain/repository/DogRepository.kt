package com.lloyd.domain.repository

import com.lloyd.data.remote.dto.DogBreedDto
import com.lloyd.data.remote.dto.DogDetailsDto

interface DogRepository {
    suspend fun getDogBreeds(): DogBreedDto
    suspend fun getDogDetailsByBreedName(dogBreedName: String): DogDetailsDto
}