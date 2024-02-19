package com.lloyd.domain.usecase

import com.lloyd.common.Result
import com.lloyd.data.remote.dto.toDogDetails
import com.lloyd.di.IoDispatcher
import com.lloyd.domain.model.DogDetails
import com.lloyd.domain.repository.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDogDetailsUseCaseImpl @Inject constructor(
    private val dogBreedRepository: DogRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GetDogDetailsUseCase {

    override fun getDogDetailsByBreedName(dogBreedName: String): Flow<Result<DogDetails>> = flow {
        try {
            emit(Result.Loading())
            val dogDetails = dogBreedRepository.getDogDetailsByBreedName(dogBreedName)
            emit(Result.Success(dogDetails.toDogDetails()))
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