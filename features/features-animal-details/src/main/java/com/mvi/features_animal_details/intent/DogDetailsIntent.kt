package com.mvi.features_animal_details.intent

sealed interface DogDetailsIntent {
    class GetDogDetails(val dogBreedName: String? = null) : DogDetailsIntent
}