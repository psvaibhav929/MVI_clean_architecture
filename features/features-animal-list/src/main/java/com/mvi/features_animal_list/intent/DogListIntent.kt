package com.mvi.features_animal_list.intent

sealed interface DogListIntent {
    object GetAnimalList : DogListIntent
    data class DogListItemClicked(
        val dogBreedName: String,
        val dogName: String,
    ) : DogListIntent
}