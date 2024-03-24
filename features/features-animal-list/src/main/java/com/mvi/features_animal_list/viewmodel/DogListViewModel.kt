package com.mvi.features_animal_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.common.di.IoDispatcher
import com.mvi.domain.result.ApiResult
import com.mvi.domain.usecase.GetDogBreedsUseCase
import com.mvi.features_animal_list.intent.DogListIntent
import com.mvi.features_animal_list.viewstate.DogListClickState
import com.mvi.features_animal_list.viewstate.DogListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetDogBreedsUseCase,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {


    private val _dogListState = MutableStateFlow<DogListViewState>(DogListViewState.Idle)
    val dogListState: StateFlow<DogListViewState>
        get() = _dogListState.asStateFlow()

    private val _dogListClickState = MutableSharedFlow<DogListClickState>()
    val dogListClickState: SharedFlow<DogListClickState>
        get() = _dogListClickState

    init {
        sendIntent(DogListIntent.GetAnimalList)
    }


    fun sendIntent(intent: DogListIntent) {
        when (intent) {
            is DogListIntent.GetAnimalList -> getDogBreeds()
            is DogListIntent.DogListItemClicked -> navigateDetailsScreen(intent)
        }
    }

    private fun navigateDetailsScreen(intent: DogListIntent.DogListItemClicked) {
        viewModelScope.launch {
            _dogListClickState.emit(
                DogListClickState.NavigateToDetailScreen(intent.dogBreedName, intent.dogName)
            )
        }
    }

     private fun getDogBreeds() {
        viewModelScope.launch(ioDispatcher) {
            when (val dogList = getDogBreedsUseCase()) {
                is ApiResult.Success -> {
                    _dogListState.emit(
                        DogListViewState.Success(
                            dogList.data.dogs
                        )
                    )
                }

                is ApiResult.Error -> {
                    val errorMessage = dogList.exception?.message ?: "An unexpected error"
                    _dogListState.emit(
                        DogListViewState.Error(
                            errorMessage
                        )
                    )
                }
            }
        }
    }

}