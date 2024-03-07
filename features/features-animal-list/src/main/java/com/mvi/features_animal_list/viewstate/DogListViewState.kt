package com.mvi.features_animal_list.viewstate

import com.mvi.domain.model.DogName

sealed class DogListViewState {
    object Idle : DogListViewState()
    object Loading : DogListViewState()
    class Error(val message: String) : DogListViewState()
    class Success(val data: List<DogName>) : DogListViewState()
}