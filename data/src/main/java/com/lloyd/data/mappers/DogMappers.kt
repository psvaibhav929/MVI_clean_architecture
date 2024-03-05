package com.lloyd.data.mappers

import com.lloyd.common.capitalizeFirstLetter
import com.lloyd.data.dto.DogBreedDto
import com.lloyd.data.dto.DogDetailsDto
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.model.DogDetails
import com.lloyd.domain.model.DogName

fun DogBreedDto.toDogBreed(): DogBreed {
    val dogBreeds = arrayListOf<DogName>()

    for ((breed, subBreeds) in message) {
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
                    dogBreedName = breed
                )
            )
        }
    }

    return DogBreed(dogBreeds)
}
fun DogDetailsDto.toDogDetails(): DogDetails {
    return DogDetails(message)
}