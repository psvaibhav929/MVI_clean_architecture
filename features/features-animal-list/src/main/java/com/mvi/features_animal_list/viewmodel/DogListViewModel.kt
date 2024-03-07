package com.mvi.features_animal_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.common.di.IoDispatcher
import com.mvi.domain.usecase.GetDogBreedsUseCase
import com.mvi.features_animal_list.intent.DogListIntent
import com.mvi.features_animal_list.viewstate.DogListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetDogBreedsUseCase,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _dogListState = MutableStateFlow<DogListViewState>(DogListViewState.Idle)
    val dogListState: StateFlow<DogListViewState> = _dogListState.asStateFlow()
    private val _dogListEvents = MutableSharedFlow<DogListIntent>()


    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            _dogListEvents.collect { intent ->
                when (intent) {
                    is DogListIntent.GetAnimalList -> withContext(ioDispatcher) {
                        _dogListState.emit(DogListViewState.Loading)
                        getDogBreeds()
                    }

                    is DogListIntent.DogListItemClicked -> {
                        intent.onClick(intent.dogBreedName, intent.dogName)
                    }

                }

            }
        }
    }

    fun sendIntent(intent: DogListIntent) {
        viewModelScope.launch {
            _dogListEvents.emit(intent)
        }
    }

    private suspend fun getDogBreeds() {
        getDogBreedsUseCase().fold(
            onSuccess = { dogList ->
                _dogListState.emit(
                    DogListViewState.Success(
                        dogList.dogs
                    )
                )

            },
            onFailure = { error ->
                _dogListState.emit(
                    DogListViewState.Error(
                        error.message ?: "An unexpected error"
                    )
                )

            }
        )
    }
}