package com.lloyd.domain.usecase

import com.lloyd.common.Result
import com.lloyd.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface GetDogBreedsUseCase {
    operator fun invoke(): Flow<Result<DogBreed>>
}