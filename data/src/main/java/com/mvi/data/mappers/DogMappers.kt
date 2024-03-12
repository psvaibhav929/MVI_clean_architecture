package com.mvi.data.mappers

import com.mvi.data.common.capitalizeFirstLetter
import com.mvi.data.dto.DogBreedDto
import com.mvi.data.dto.DogDetailsDto
import com.mvi.domain.model.DogBreed
import com.mvi.domain.model.DogDetails
import com.mvi.domain.model.DogName
import javax.inject.Inject

class DogMappers @Inject constructor(){
    fun toDogBreed(dto: DogBreedDto): DogBreed {
        val dogBreeds = arrayListOf<DogName>()

        for ((breed, subBreeds) in dto.message) {
            val capitalizedBreedName = breed.capitalizeFirstLetter()

            //if there are more than one sub breeds then only add in the list
            if (subBreeds.size > 1) {
                dogBreeds.addAll(
                    subBreeds.map {
                        DogName(
                            dogFullName = "$capitalizedBreedName - $it",
                            dogBreedName = breed,
                            dogSubBreedName = it
                        )
                    }
                )
            } else {
                dogBreeds.add(
                    DogName(
                        dogFullName = capitalizedBreedName,
                        dogBreedName = breed,
                        dogSubBreedName = ""
                    )
                )
            }
        }

        return DogBreed(dogBreeds)
    }
    fun toDogDetails(dto: DogDetailsDto): DogDetails {
        return DogDetails(dto.message)
    }
}
