package com.lloyd.domain.usecase

import com.lloyd.common.ErrorFactory
import com.lloyd.common.Result
import com.lloyd.domain.di.IoDispatcher
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.repository.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDogBreedsUseCaseImpl @Inject constructor(
    private val dogBreedRepository: DogRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GetDogBreedsUseCase {

    override fun getDogBreeds(): Flow<Result<DogBreed>> = flow {
        try {
            emit(Result.Loading())
            val dogBreeds = dogBreedRepository.getDogBreeds()
            emit(Result.Success(dogBreeds))
        }
        catch (e: Exception) {
            val errorMessage = ErrorFactory.getErrorMessage(e)
            emit(Result.Error(errorMessage))
        }
    }.flowOn(ioDispatcher)
}