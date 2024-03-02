package com.lloyd.domain.usecase

import com.lloyd.common.Result
import com.lloyd.domain.model.DogDetails
import kotlinx.coroutines.flow.Flow

interface GetDogDetailsUseCase {
    fun getDogDetailsByBreedName(dogBreedName : String): Flow<Result<DogDetails>>
}