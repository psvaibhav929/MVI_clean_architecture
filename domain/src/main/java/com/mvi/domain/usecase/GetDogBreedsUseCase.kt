package com.mvi.domain.usecase

import com.mvi.common.ApiResult
import com.mvi.domain.model.DogBreed
import com.mvi.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDogBreedsUseCase @Inject constructor(
    private val dogBreedRepository: DogRepository,
) {
    operator fun invoke(): Flow<ApiResult<DogBreed>> = flow {
        emit(ApiResult.Loading())
        emit(dogBreedRepository.getDogBreeds())
        Result
    }
}