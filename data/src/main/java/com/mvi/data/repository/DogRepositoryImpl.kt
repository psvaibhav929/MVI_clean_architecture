package com.mvi.data.repository

import com.mvi.data.mappers.DogMappers
import com.mvi.data.network.SafeApiCall
import com.mvi.data.services.DogService
import com.mvi.domain.model.DogBreed
import com.mvi.domain.model.DogDetails
import com.mvi.domain.repository.DogRepository
import javax.inject.Inject


class DogRepositoryImpl @Inject constructor(
    private val dogApi: DogService,
    private val dogMappers: DogMappers
) : DogRepository {

    override suspend fun getDogBreeds(): Result<DogBreed> {
        return SafeApiCall.call({ dogApi.getAllBreeds() }) { dogBreedDto ->
            dogMappers.toDogBreed(
                dogBreedDto
            )
        }
    }


    override suspend fun getDogDetailsByBreedName(dogBreedName: String): Result<DogDetails> {
        return SafeApiCall.call({ dogApi.getDogDetailsByBreedName(dogBreedName) }) { dogDetailsDto ->
            dogMappers.toDogDetails(
                dogDetailsDto
            )
        }
    }
}
