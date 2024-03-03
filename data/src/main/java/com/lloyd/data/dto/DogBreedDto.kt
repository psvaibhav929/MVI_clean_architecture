package com.lloyd.data.dto

import android.os.Parcelable
import com.lloyd.common.capitalizeFirstLetter
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.model.DogName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogBreedDto(
    val message: Map<String, List<String>>,
    val status: String
) : Parcelable


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