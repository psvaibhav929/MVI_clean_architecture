package com.lloyd.features_animal_details.mockdata

import com.google.gson.Gson
import com.lloyd.common.test.TestHelper
import com.lloyd.domain.model.DogDetails

private const val dogDetailsMockJson = "dog_details.json"

private val gson by lazy { Gson() }

fun fetchDogDetailsMockData(): DogDetails {
    val content = TestHelper.readFileResource("/$dogDetailsMockJson")
    return gson.fromJson(content, DogDetails::class.java)
}