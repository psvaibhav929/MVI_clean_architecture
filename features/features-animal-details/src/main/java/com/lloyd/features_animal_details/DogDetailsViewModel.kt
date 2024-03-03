package com.lloyd.features_animal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyd.common.Result
import com.lloyd.domain.usecase.GetDogDetailsUseCase
import com.lloyd.features_animal_details.viewstate.DogDetailsViewState
import com.lloyd.features_animal_list.intent.DogDetailsIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
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
    val dogDetailsIntent: Channel<DogDetailsIntent> = Channel(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    fun sendIntent(intent: DogDetailsIntent) {
        viewModelScope.launch {
            dogDetailsIntent.send(intent)
        }
    }


    private fun handleIntent() {
        viewModelScope.launch {
            dogDetailsIntent.consumeAsFlow().collect {
                when (it) {
                    is DogDetailsIntent.GetDogDetails -> {
                        if (!it.dogBreedName.isNullOrBlank()) {
                            getDogDetailsByBreedName(it.dogBreedName)
                        }
                    }
                }
            }
        }
    }

    fun getDogDetailsByBreedName(dogBreedName: String) {
        getDogDetailsUseCase(dogBreedName).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _dogDetailsState.value = DogDetailsViewState.Success(
                        dogImageUrl = result.data?.dogImageUrl
                    )
                }

                is Result.Error -> {
                    _dogDetailsState.value = DogDetailsViewState.Error(
                        result.message ?: "An unexpected error"
                    )
                }

                is Result.Loading -> {
                    _dogDetailsState.value = DogDetailsViewState.Loading
                }
            }
        }.launchIn(viewModelScope)
    }
}