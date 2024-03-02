package com.lloyd.domain.usecase

import com.lloyd.common.Result
import com.lloyd.domain.repository.dto.toDogBreed
import com.lloyd.domain.di.IoDispatcher
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.repository.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDogBreedsUseCaseImpl @Inject constructor(
    private val dogBreedRepository: DogRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GetDogBreedsUseCase {

    override fun getDogBreeds(): Flow<Result<DogBreed>> = flow {
        try {
            emit(Result.Loading())
            val dogBreeds = dogBreedRepository.getDogBreeds()
            emit(Result.Success(dogBreeds.toDogBreed()))
        } catch (e: HttpException) {
            emit(
                Result.Error(
                    message = e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                Result.Error(
                    message = "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }.flowOn(ioDispatcher)
}