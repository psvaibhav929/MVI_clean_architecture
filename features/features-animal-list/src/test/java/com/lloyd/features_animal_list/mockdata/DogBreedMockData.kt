package com.lloyd.features_animal_list.mockdata

import com.google.gson.Gson
import com.lloyd.domain.model.DogBreed


val dogBreedsMockJson = """
    {
  "dogs": [
    {
      "dogFullName": "Affenpinscher",
      "dogBreedName": "affenpinscher"
    },
    {
      "dogFullName": "Australian Kelpie",
      "dogBreedName": "australian",
      "dogSubBreedName": "kelpie"
    },
    {
      "dogFullName": "Australian Shepherd",
      "dogBreedName": "australian",
      "dogSubBreedName": "shepherd"
    },
    {
      "dogFullName": "Bakharwal Dog (Indian)",
      "dogBreedName": "bakharwal",
      "dogSubBreedName": "indian"
    },
    {
      "dogFullName": "Boston Bulldog",
      "dogBreedName": "bulldog",
      "dogSubBreedName": "boston"
    },
    {
      "dogFullName": "English Bulldog",
      "dogBreedName": "bulldog",
      "dogSubBreedName": "english"
    },
    {
      "dogFullName": "French Bulldog",
      "dogBreedName": "bulldog",
      "dogSubBreedName": "french"
    },
    {
      "dogFullName": "Norwegian Buhund",
      "dogBreedName": "buhund",
      "dogSubBreedName": "norwegian"
    },
    {
      "dogFullName": "Staffordshire Bullterrier",
      "dogBreedName": "bullterrier",
      "dogSubBreedName": "staffordshire"
    },
    {
      "dogFullName": "Australian Cattledog",
      "dogBreedName": "cattledog",
      "dogSubBreedName": "australian"
    },
    {
      "dogFullName": "Chippiparai Dog (Indian)",
      "dogBreedName": "chippiparai",
      "dogSubBreedName": "indian"
    }
  ]
}
""".trimIndent()

private val gson by lazy { Gson() }

fun fetchDogBreedsMockData(): DogBreed {
    return gson.fromJson< DogBreed>(dogBreedsMockJson,  DogBreed::class.java)
}
