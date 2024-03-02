package com.lloyd.features_animal_list.intent

sealed class DogDetailsIntent {
    class GetDogDetails(val dogBreedName : String? = null) : DogDetailsIntent()
}