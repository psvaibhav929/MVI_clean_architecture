package com.mvi.features_animal_list.viewstate

sealed class DogListClickState {
    data class NavigateToDetailScreen(val breedName: String, val dogName: String) : DogListClickState()
}