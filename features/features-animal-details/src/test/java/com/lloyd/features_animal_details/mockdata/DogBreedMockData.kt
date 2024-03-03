package com.lloyd.features_animal_details.mockdata

 import com.google.gson.Gson
 import com.lloyd.domain.model.DogDetails


val dogDetailsMockJson = """
    {
        "dogImageUrl": "https://example.com/labrador.jpg"
    }
""".trimIndent()

private val gson by lazy { Gson() }

fun fetchDogDetailsMockData(): DogDetails {
 return gson.fromJson(dogDetailsMockJson, DogDetails::class.java)
}