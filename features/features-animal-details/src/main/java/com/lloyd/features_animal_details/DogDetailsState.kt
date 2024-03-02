package com.lloyd.features_animal_details

data class DogDetailsState(
    val isLoading: Boolean = false,
    val dogImageUrl: String? = null,
    val error: String? = null
)
