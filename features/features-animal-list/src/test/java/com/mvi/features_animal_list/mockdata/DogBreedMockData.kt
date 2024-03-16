package com.mvi.features_animal_list.mockdata

import com.google.gson.Gson
import test.TestHelper
import com.mvi.domain.model.DogBreed

private const val dogBreedsMockJson = "dog_breeds.json"
const val GRT_DOG_BREEDS_ERROR ="Error fetching dog breeds"

private val gson by lazy { Gson() }

fun fetchDogBreedsMockData(): DogBreed {
    val content = TestHelper.readFileResource("/$dogBreedsMockJson")
    return gson.fromJson(content,  DogBreed::class.java)
}
