package com.lloyd.domain.usecase

import com.lloyd.common.ApiResult
import com.lloyd.common.di.IoDispatcher
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.repository.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDogBreedsUseCase @Inject constructor(
    private val dogBreedRepository: DogRepository,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<ApiResult<DogBreed>> = flow {
        emit(ApiResult.Loading())
        emit(dogBreedRepository.getDogBreeds())
    }.flowOn(ioDispatcher)
}