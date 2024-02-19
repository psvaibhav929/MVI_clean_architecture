package com.lloyd.data.repository

import com.lloyd.data.remote.DogApi
import com.lloyd.data.remote.dto.DogBreedDto
import com.lloyd.data.remote.dto.DogDetailsDto
import com.lloyd.domain.repository.DogRepository
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val dogApi: DogApi
) : DogRepository {

    override suspend fun getDogBreeds(): DogBreedDto {
        return dogApi.getAllBreeds()
    }

    override suspend fun getDogDetailsByBreedName(dogBreedName: String): DogDetailsDto {
        return dogApi.getDogDetailsByBreedName(dogBreedName)
    }
}