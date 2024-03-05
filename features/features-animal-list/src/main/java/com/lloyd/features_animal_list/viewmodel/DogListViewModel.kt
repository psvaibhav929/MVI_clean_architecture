package com.lloyd.features_animal_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyd.common.Result
import com.lloyd.domain.usecase.GetDogBreedsUseCase
import com.lloyd.features_animal_list.intent.DogListIntent
import com.lloyd.features_animal_list.viewstate.DogListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetDogBreedsUseCase,
) : ViewModel() {
    private var _dogListState = MutableStateFlow<DogListViewState>(DogListViewState.Idle)
    val dogListState: StateFlow<DogListViewState> = _dogListState.asStateFlow()

    init {
        sendIntent(DogListIntent.GetAnimalList)
    }


    fun sendIntent(intent: DogListIntent) {
        viewModelScope.launch {
            when (intent) {
                is DogListIntent.GetAnimalList -> getDogBreeds()
            }

        }
    }

    private fun getDogBreeds() {
        getDogBreedsUseCase().onEach { result ->
            when (result) {
                is Result.Success -> {
                    _dogListState.value = DogListViewState.Success(
                        result.data?.dogs ?: emptyList()
                    )
                }

                is Result.Error -> {
                    _dogListState.value = DogListViewState.Error(
                        result.message ?: "An unexpected error"
                    )
                }

                is Result.Loading -> {
                    _dogListState.value = DogListViewState.Loading
                }
            }
        }.launchIn(viewModelScope)
    }
}