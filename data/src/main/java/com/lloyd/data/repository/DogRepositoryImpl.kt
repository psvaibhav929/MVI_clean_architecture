package com.lloyd.data.repository

import com.lloyd.data.services.DogService
import com.lloyd.domain.repository.DogRepository
import com.lloyd.data.dto.toDogBreed
import com.lloyd.data.dto.toDogDetails
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.model.DogDetails
import javax.inject.Inject


class DogRepositoryImpl @Inject constructor(
    private val dogApi: DogService
) : DogRepository {

    override suspend fun getDogBreeds(): DogBreed {
        return dogApi.getAllBreeds().toDogBreed()
    }

    override suspend fun getDogDetailsByBreedName(dogBreedName: String): DogDetails {
        return dogApi.getDogDetailsByBreedName(dogBreedName).toDogDetails()
    }
}