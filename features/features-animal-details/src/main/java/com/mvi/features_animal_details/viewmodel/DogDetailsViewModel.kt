package com.mvi.features_animal_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.common.constant.Constants
import com.mvi.common.di.IoDispatcher
import com.mvi.domain.result.ApiResult
import com.mvi.domain.usecase.GetDogDetailsUseCase
import com.mvi.features_animal_details.intent.DogDetailsIntent
import com.mvi.features_animal_details.viewstate.DogDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailsViewModel @Inject constructor(
    private val getDogDetailsUseCase: GetDogDetailsUseCase,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dogDetailsState = MutableStateFlow<DogDetailsViewState>(DogDetailsViewState.Idle)
    val dogDetailsState: StateFlow<DogDetailsViewState> = _dogDetailsState.asStateFlow()

    init {
        sendIntent(DogDetailsIntent.GetDogDetails)
    }

    fun sendIntent(intent: DogDetailsIntent) {
        when (intent) {
            is DogDetailsIntent.GetDogDetails -> {
                getDogDetailsByBreedName(
                    savedStateHandle.get<String>(Constants.PARAM_DOG_BREED_NAME) ?: ""
                )
            }
        }
    }

    private fun getDogDetailsByBreedName(dogBreedName: String) {
        viewModelScope.launch(ioDispatcher) {
            _dogDetailsState.emit(DogDetailsViewState.Loading)
            when (val dogBreedDetails = getDogDetailsUseCase(dogBreedName)) {
                is ApiResult.Success -> {
                    _dogDetailsState.emit(
                        DogDetailsViewState.Success(
                            dogImageUrl = dogBreedDetails.data.dogImageUrl
                        )
                    )
                }

                is ApiResult.Error -> {
                    _dogDetailsState.emit(
                        DogDetailsViewState.Error(
                            dogBreedDetails.exception?.message ?: "An unexpected error"
                        )
                    )

                }
            }
        }

    }
}