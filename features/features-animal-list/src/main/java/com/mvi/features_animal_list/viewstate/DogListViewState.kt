package com.mvi.features_animal_list.viewstate

import com.mvi.domain.model.DogName

sealed interface DogListViewState {
    object Idle : DogListViewState
    object Loading : DogListViewState
    data class Error(val message: String) : DogListViewState
    data class Success(val data: List<DogName>) : DogListViewState

}