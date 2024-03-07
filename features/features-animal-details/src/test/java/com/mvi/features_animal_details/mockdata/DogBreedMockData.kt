package com.mvi.features_animal_details.mockdata

import com.google.gson.Gson
import test.TestHelper
import com.mvi.domain.model.DogDetails

private const val dogDetailsMockJson = "dog_details.json"

private val gson by lazy { Gson() }

fun fetchDogDetailsMockData(): DogDetails {
    val content = TestHelper.readFileResource("/$dogDetailsMockJson")
    return gson.fromJson(content, DogDetails::class.java)
}