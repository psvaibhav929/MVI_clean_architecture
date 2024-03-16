package com.mvi.domain.mockdata

import com.google.gson.Gson
import test.TestHelper
import com.mvi.domain.model.DogBreed
import com.mvi.domain.model.DogDetails


private const val dogBreedsMockJson =  "dog_breed.json"


private val gson by lazy { Gson() }

fun fetchDogBreedsMockData(): DogBreed {
    return gson.fromJson(TestHelper.readFileResource("/$dogBreedsMockJson"), DogBreed::class.java)
}

private const val dogDetailsMockJson = "dog_detail.json"
fun fetchDogDetailsMockData(): DogDetails {
    return gson.fromJson(TestHelper.readFileResource("/$dogDetailsMockJson"), DogDetails::class.java)
}