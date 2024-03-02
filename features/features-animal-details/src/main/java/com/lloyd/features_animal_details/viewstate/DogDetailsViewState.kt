package com.lloyd.features_animal_details.viewstate

import com.lloyd.domain.model.DogName

sealed class DogDetailsViewState {
    object Idle : DogDetailsViewState()
    object Loading : DogDetailsViewState()
    class Error(val message: String) : DogDetailsViewState()
    class Success(val dogImageUrl: String?) : DogDetailsViewState()
}