package com.mvi.features_animal_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.common.ApiResult
import com.mvi.common.di.IoDispatcher
import com.mvi.domain.usecase.GetDogBreedsUseCase
import com.mvi.features_animal_list.intent.DogListIntent
import com.mvi.features_animal_list.viewstate.DogListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetDogBreedsUseCase,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _dogListState = MutableSharedFlow<DogListViewState>()
    val dogListState: SharedFlow<DogListViewState> = _dogListState

    init {
        sendIntent(DogListIntent.GetAnimalList)
    }


    fun sendIntent(intent: DogListIntent) {
        viewModelScope.launch {
            when (intent) {
                is DogListIntent.GetAnimalList -> withContext(ioDispatcher){
                    getDogBreeds()
                }
            }

        }
    }

    private fun getDogBreeds() {
        getDogBreedsUseCase().onEach { result ->
            when (result) {
                is ApiResult.Success -> {
                    _dogListState.emit(
                        DogListViewState.Success(
                            result.data?.dogs ?: emptyList()
                        )
                    )
                }

                is ApiResult.Error -> {
                    _dogListState.emit(
                        DogListViewState.Error(
                            result.message ?: "An unexpected error"
                        )
                    )
                }

                is ApiResult.Loading -> {
                    _dogListState.emit(DogListViewState.Loading)
                }
            }
        }.launchIn(viewModelScope)
    }
}