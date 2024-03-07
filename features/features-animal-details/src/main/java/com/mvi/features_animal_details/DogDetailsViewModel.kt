package com.mvi.features_animal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.common.di.IoDispatcher
import com.mvi.domain.usecase.GetDogDetailsUseCase
import com.mvi.features_animal_details.intent.DogDetailsIntent
import com.mvi.features_animal_details.viewstate.DogDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailsViewModel @Inject constructor(
    private val getDogDetailsUseCase: GetDogDetailsUseCase,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _dogDetailsState = MutableStateFlow<DogDetailsViewState>(DogDetailsViewState.Idle)
    val dogDetailsState: StateFlow<DogDetailsViewState> = _dogDetailsState.asStateFlow()

    private val _dogDetailsEvents = MutableSharedFlow<DogDetailsIntent>()

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch(ioDispatcher) {
            _dogDetailsEvents.collect { intent ->
                when (intent) {
                    is DogDetailsIntent.GetDogDetails -> {
                        if (!intent.dogBreedName.isNullOrBlank()) {
                            _dogDetailsState.emit(DogDetailsViewState.Loading)
                            getDogDetailsByBreedName(intent.dogBreedName)
                        }
                    }
                }
            }
        }
    }
    fun sendIntent(intent: DogDetailsIntent) {
        viewModelScope.launch {
            _dogDetailsEvents.emit(intent)
        }
    }


    private suspend fun getDogDetailsByBreedName(dogBreedName: String) {
        getDogDetailsUseCase(dogBreedName).fold(
            onSuccess = { dogDetails ->
                _dogDetailsState.emit(
                    DogDetailsViewState.Success(
                        dogImageUrl = dogDetails.dogImageUrl
                    )
                )
            },
            onFailure = { error ->
                _dogDetailsState.emit(
                    DogDetailsViewState.Error(
                        error.message ?: "An unexpected error"
                    )
                )
            }
        )


    }
}