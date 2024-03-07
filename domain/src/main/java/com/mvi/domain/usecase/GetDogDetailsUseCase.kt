package com.mvi.domain.usecase

import com.mvi.common.ApiResult
import com.mvi.domain.model.DogDetails
import com.mvi.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDogDetailsUseCase @Inject constructor(
    private val dogBreedRepository: DogRepository,
) {
    operator fun invoke(dogBreedName: String): Flow<ApiResult<DogDetails>> = flow {
        emit(ApiResult.Loading())
        emit(dogBreedRepository.getDogDetailsByBreedName(dogBreedName))
    }


}