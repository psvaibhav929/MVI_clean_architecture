package com.lloyd.domain.usecase

import com.lloyd.common.ErrorFactory
import com.lloyd.common.Result
import com.lloyd.common.di.IoDispatcher
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.model.DogDetails
import com.lloyd.domain.repository.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDogDetailsUseCase @Inject constructor(
    private val dogBreedRepository: DogRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(dogBreedName: String): Flow<Result<DogDetails>> = flow {
        emit(Result.Loading())
        emit(dogBreedRepository.getDogDetailsByBreedName(dogBreedName))
    }.flowOn(ioDispatcher)


}