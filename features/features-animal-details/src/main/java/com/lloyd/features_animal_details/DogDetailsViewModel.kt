package com.lloyd.features_animal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyd.common.ApiResult
import com.lloyd.domain.usecase.GetDogDetailsUseCase
import com.lloyd.features_animal_details.intent.DogDetailsIntent
import com.lloyd.features_animal_details.viewstate.DogDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailsViewModel @Inject constructor(
    private val getDogDetailsUseCase: GetDogDetailsUseCase,
) : ViewModel() {
    private var _dogDetailsState = MutableStateFlow<DogDetailsViewState>(DogDetailsViewState.Idle)
    val dogDetailsState: StateFlow<DogDetailsViewState> = _dogDetailsState.asStateFlow()

    fun sendIntent(intent: DogDetailsIntent) {
        viewModelScope.launch {
            when (intent) {
                is DogDetailsIntent.GetDogDetails -> {
                    if (!intent.dogBreedName.isNullOrBlank()) {
                        getDogDetailsByBreedName(intent.dogBreedName)
                    }
                }
            }

        }
    }

    private fun getDogDetailsByBreedName(dogBreedName: String) {
        getDogDetailsUseCase(dogBreedName).onEach { result ->
            when (result) {
                is ApiResult.Success -> {
                    _dogDetailsState.value = DogDetailsViewState.Success(
                        dogImageUrl = result.data?.dogImageUrl
                    )
                }

                is ApiResult.Error -> {
                    _dogDetailsState.value = DogDetailsViewState.Error(
                        result.message ?: "An unexpected error"
                    )
                }

                is ApiResult.Loading -> {
                    _dogDetailsState.value = DogDetailsViewState.Loading
                }
            }
        }.launchIn(viewModelScope)
    }
}