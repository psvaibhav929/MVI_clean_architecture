package com.lloyd.domain.usecase

import com.lloyd.common.ErrorFactory
import com.lloyd.common.Result
import com.lloyd.domain.di.IoDispatcher
import com.lloyd.domain.model.DogDetails
import com.lloyd.domain.repository.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDogDetailsUseCaseImpl @Inject constructor(
    private val dogBreedRepository: DogRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GetDogDetailsUseCase {

    override fun getDogDetailsByBreedName(dogBreedName: String): Flow<Result<DogDetails>> = flow {
        try {
            emit(Result.Loading())
            val dogDetails = dogBreedRepository.getDogDetailsByBreedName(dogBreedName)
            emit(Result.Success(dogDetails))
        } catch (e: Exception) {
            val errorMessage = ErrorFactory.getErrorMessage(e)
            emit(Result.Error(errorMessage))
        }
    }.flowOn(ioDispatcher)
}