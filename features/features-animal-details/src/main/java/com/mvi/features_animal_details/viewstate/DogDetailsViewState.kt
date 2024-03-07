package com.mvi.features_animal_details.viewstate

sealed class DogDetailsViewState {
    object Idle : DogDetailsViewState()
    object Loading : DogDetailsViewState()
    class Error(val message: String) : DogDetailsViewState()
    class Success(val dogImageUrl: String?) : DogDetailsViewState()
}