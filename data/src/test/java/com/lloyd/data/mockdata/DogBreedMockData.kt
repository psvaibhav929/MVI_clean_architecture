package com.lloyd.data.mockdata

import com.google.gson.Gson
import com.lloyd.common.test.TestHelper
import com.lloyd.data.dto.DogBreedDto
import com.lloyd.data.dto.DogDetailsDto


private const val dogBreedsMockJson = "dog_breed.json"

private val gson by lazy { Gson() }

fun fetchDogBreedsMockData(): DogBreedDto {
    return gson.fromJson(
        TestHelper.readFileResource("/$dogBreedsMockJson"),
        DogBreedDto::class.java
    )
}

private const val dogDetailsMockJson = "dog_details.json"
fun fetchDogDetailsMockData(): DogDetailsDto {
    return gson.fromJson(TestHelper.readFileResource("/$dogDetailsMockJson"), DogDetailsDto::class.java)
}