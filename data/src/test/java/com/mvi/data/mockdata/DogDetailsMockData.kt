package com.mvi.data.mockdata

import com.google.gson.Gson
import com.mvi.data.dto.DogDetailsDto
import com.mvi.domain.model.DogDetails
import test.TestHelper


private const val DOG_DETAILS_DTO = "dog_details_dto.json"
private const val DOG_DETAILS_EXPECTED = "dog_details_expected.json"

private val gson by lazy { Gson() }
fun loadDogDetailsDto(): DogDetailsDto {
    return gson.fromJson(
        TestHelper.readFileResource("/$DOG_DETAILS_DTO"),
        DogDetailsDto::class.java
    )
}
fun loadDogDetailsExpected(): DogDetails {
    return gson.fromJson(
        TestHelper.readFileResource("/$DOG_DETAILS_EXPECTED"),
        DogDetails::class.java
    )
}
