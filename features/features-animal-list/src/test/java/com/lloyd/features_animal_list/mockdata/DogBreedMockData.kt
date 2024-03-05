package com.lloyd.features_animal_list.mockdata

import com.google.gson.Gson
import com.lloyd.common.test.TestHelper
import com.lloyd.domain.model.DogBreed

private const val dogBreedsMockJson = "dog_breeds.json"

private val gson by lazy { Gson() }

fun fetchDogBreedsMockData(): DogBreed {
    val content = TestHelper.readFileResource("/$dogBreedsMockJson")
    return gson.fromJson(content,  DogBreed::class.java)
}
