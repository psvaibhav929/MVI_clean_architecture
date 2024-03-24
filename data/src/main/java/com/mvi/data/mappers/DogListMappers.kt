package com.mvi.data.mappers

import com.mvi.data.dto.DogBreedDto
import com.mvi.domain.model.DogBreed
import com.mvi.domain.model.DogName
import javax.inject.Inject

class DogListMappers @Inject constructor() {
    fun toDogBreed(dto: DogBreedDto): DogBreed {
        val dogBreeds = arrayListOf<DogName>()

        for ((breed, subBreeds) in dto.message) {

            //if there are more than one sub breeds then only add in the list
            if (subBreeds.size > 1) {
                dogBreeds.addAll(
                    subBreeds.map {
                        DogName(
                            dogFullName = "$breed - $it",
                            dogBreedName = breed,
                            dogSubBreedName = it
                        )
                    }
                )
            } else {
                dogBreeds.add(
                    DogName(
                        dogFullName = breed,
                        dogBreedName = breed,
                        dogSubBreedName = subBreeds.firstOrNull() ?: ""
                    )
                )
            }
        }

        return DogBreed(dogBreeds)
    }

}