package com.mvi.data.mockdata

import com.google.gson.Gson
import test.TestHelper
import com.mvi.data.dto.DogBreedDto
import com.mvi.data.dto.DogDetailsDto


private const val dogBreedsMockJson = "dog_breed.json"
private const val dogDetailsMockJson = "dog_details.json"

private val gson by lazy { Gson() }

fun fetchDogBreedsMockData(): DogBreedDto {
    return gson.fromJson(
        TestHelper.readFileResource("/$dogBreedsMockJson"),
        DogBreedDto::class.java
    )
}
fun fetchDogDetailsMockData(): DogDetailsDto {
    return gson.fromJson(TestHelper.readFileResource("/$dogDetailsMockJson"), DogDetailsDto::class.java)
}
