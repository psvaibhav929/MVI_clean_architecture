package com.mvi.domain.usecase

import com.mvi.domain.repository.DogRepository
import javax.inject.Inject

class GetDogBreedsUseCase @Inject constructor(
    private val dogBreedRepository: DogRepository,
) {
    suspend operator fun invoke() = dogBreedRepository.getDogBreeds()
}