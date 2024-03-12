package com.mvi.features_animal_details.viewstate

sealed interface DogDetailsViewState {
    object Idle : DogDetailsViewState
    object Loading : DogDetailsViewState
    data class Error(val message: String) : DogDetailsViewState
    data class Success(val dogImageUrl: String?) : DogDetailsViewState
}