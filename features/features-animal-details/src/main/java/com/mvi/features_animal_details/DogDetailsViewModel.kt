package com.mvi.features_animal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.common.ApiResult
import com.mvi.common.di.IoDispatcher
import com.mvi.domain.usecase.GetDogDetailsUseCase
import com.mvi.features_animal_details.intent.DogDetailsIntent
import com.mvi.features_animal_details.viewstate.DogDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailsViewModel @Inject constructor(
    private val getDogDetailsUseCase: GetDogDetailsUseCase,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _dogDetailsState = MutableSharedFlow<DogDetailsViewState>()
    val dogDetailsState: SharedFlow<DogDetailsViewState> = _dogDetailsState


    fun sendIntent(intent: DogDetailsIntent) {
        viewModelScope.launch(ioDispatcher) {
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
                    _dogDetailsState.emit(DogDetailsViewState.Success(
                        dogImageUrl = result.data?.dogImageUrl
                    ))
                }

                is ApiResult.Error -> {
                    _dogDetailsState.emit(DogDetailsViewState.Error(
                        result.message ?: "An unexpected error"
                    ))
                }

                is ApiResult.Loading -> {
                    _dogDetailsState.emit(DogDetailsViewState.Loading)
                }
            }
        }.launchIn(viewModelScope)
    }
}