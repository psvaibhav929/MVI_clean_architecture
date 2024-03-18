package com.mvi.data.repository

import com.mvi.data.mappers.DogDetailsMappers
import com.mvi.data.mappers.DogListMappers
import com.mvi.data.network.SafeApiCall
import com.mvi.data.services.DogService
import com.mvi.domain.model.DogBreed
import com.mvi.domain.model.DogDetails
import com.mvi.domain.repository.DogRepository
import com.mvi.domain.result.ApiResult
import javax.inject.Inject


class DogRepositoryImpl @Inject constructor(
    private val dogApi: DogService,
    private val dogDetailsMappers: DogDetailsMappers,
    private val dogListMappers: DogListMappers
) : DogRepository {

    override suspend fun getDogBreeds(): ApiResult<DogBreed> {
        return SafeApiCall.call(
            { dogApi.getAllBreeds() },
            { dogBreedDto -> dogListMappers.toDogBreed(dogBreedDto) })
    }


    override suspend fun getDogDetailsByBreedName(dogBreedName: String): ApiResult<DogDetails> {
        return SafeApiCall.call({ dogApi.getDogDetailsByBreedName(dogBreedName) }) { dogDetailsDto ->
            dogDetailsMappers.toDogDetails(
                dogDetailsDto
            )
        }
    }
}
