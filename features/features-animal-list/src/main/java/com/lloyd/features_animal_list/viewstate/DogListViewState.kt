package com.lloyd.features_animal_list.viewstate

import com.lloyd.domain.model.DogName

sealed class DogListViewState {
    object Idle : DogListViewState()
    object Loading : DogListViewState()
    class Error(val message: String) : DogListViewState()
    class Success(val data: List<DogName>) : DogListViewState()
}