package com.mvi.data.mockdata

import com.google.gson.Gson
import com.mvi.data.dto.DogBreedDto
import com.mvi.domain.model.DogBreed
import test.TestHelper

private const val MULTIPLE_SUB_BREED_DATA = "multiple_sub_breeds.json"
private const val MULTIPLE_SUB_BREED_EXPECTED_DATA = "multiple_sub_breeds_expected.json"
private const val SINGLE_SUB_BREED_DATA = "single_sub_breed.json"
private const val SINGLE_SUB_BREED_EXPECTED_DATA = "single_sub_breed_expected.json"
private const val NO_SUB_BREED_DATA = "no_sub_breeds.json"
private const val NO_SUB_BREED_EXPECTED_DATA = "no_sub_breeds_expected.json"
private const val EMPTY_DATA = "empty_input.json"
private const val EMPTY_SUB_BREED = "empty_sub_breed.json"
private const val EMPTY_SUB_BREED_EXPECTED = "empty_sub_breed_expected.json"

private val gson by lazy { Gson() }
fun loadMultipleSubBreeds(): DogBreedDto {
    return gson.fromJson(
        TestHelper.readFileResource("/$MULTIPLE_SUB_BREED_DATA"),
        DogBreedDto::class.java
    )
}
fun loadMultipleSubBreedsExpected(): DogBreed {
    return gson.fromJson(
        TestHelper.readFileResource("/$MULTIPLE_SUB_BREED_EXPECTED_DATA"),
        DogBreed::class.java
    )
}
 fun loadSingleSubBreed(): DogBreedDto {
    return gson.fromJson(
        TestHelper.readFileResource("/$SINGLE_SUB_BREED_DATA"),
        DogBreedDto::class.java
    )
}
 fun loadSingleSubBreedExpected(): DogBreed {
    return gson.fromJson(
        TestHelper.readFileResource("/$SINGLE_SUB_BREED_EXPECTED_DATA"),
        DogBreed::class.java
    )
}
 fun loadNoSubBreeds(): DogBreedDto {
    return gson.fromJson(
        TestHelper.readFileResource("/$NO_SUB_BREED_DATA"),
        DogBreedDto::class.java
    )
}
 fun loadNoSubBreedsExpected(): DogBreed {
    return gson.fromJson(
        TestHelper.readFileResource("/$NO_SUB_BREED_EXPECTED_DATA"),
        DogBreed::class.java
    )
}
 fun loadEmptyInput(): DogBreedDto {
    return gson.fromJson(
        TestHelper.readFileResource("/$EMPTY_DATA"),
        DogBreedDto::class.java
    )
}
fun loadEmptySubBreed(): DogBreedDto {
    return gson.fromJson(
        TestHelper.readFileResource("/$EMPTY_SUB_BREED"),
        DogBreedDto::class.java
    )
}
fun loadEmptySubBreedExpected(): DogBreed {
    return gson.fromJson(
        TestHelper.readFileResource("/$EMPTY_SUB_BREED_EXPECTED"),
        DogBreed::class.java
    )
}
