package com.lloyd.domain.usecase

import com.lloyd.common.Result
import com.lloyd.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface GetDogBreedsUseCase {
    fun getDogBreeds(): Flow<Result<DogBreed>>
}