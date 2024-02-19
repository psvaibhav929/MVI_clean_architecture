package com.lloyd.presentation.dog_list

import com.lloyd.domain.model.DogName

data class DogListState(
    val isLoading: Boolean = false,
    val dogBreeds: List<DogName> = emptyList(),
    val error: String? = null
)
