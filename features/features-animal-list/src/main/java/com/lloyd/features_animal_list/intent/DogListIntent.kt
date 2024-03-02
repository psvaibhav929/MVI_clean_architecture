package com.lloyd.features_animal_list.intent

sealed class DogListIntent {
    object GetAnimalList : DogListIntent()
}