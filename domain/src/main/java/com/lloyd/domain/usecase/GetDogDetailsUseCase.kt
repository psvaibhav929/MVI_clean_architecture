package com.lloyd.domain.usecase

import com.lloyd.common.Result
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.model.DogDetails
import kotlinx.coroutines.flow.Flow

interface GetDogDetailsUseCase {
    operator fun invoke(dogBreedName : String): Flow<Result<DogDetails>>
}