package com.lloyd.features_animal_details.intent

sealed class DogDetailsIntent {
    class GetDogDetails(val dogBreedName : String? = null) : DogDetailsIntent()
}