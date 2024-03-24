package com.mvi.features_animal_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvi.features_animal_list.intent.DogListIntent
import com.mvi.features_animal_list.viewmodel.DogListViewModel
import com.mvi.features_animal_list.viewstate.DogListClickState
import com.mvi.features_animal_list.viewstate.DogListViewState


const val TEST_TAG_DOG_LIST_SCREEN = "dog_list_screen"

@Composable
fun DogListScreen(

    callback: (dogBreed: String, dogName: String) -> Unit,
    viewModel: DogListViewModel = hiltViewModel(),

    ) {
    val state by viewModel.dogListState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TEST_TAG_DOG_LIST_SCREEN)
    ) {
        when (val currentState = state) {
            is DogListViewState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(currentState.data) { index, dogName ->
                        DogListItem(
                            modifier = Modifier.fillMaxWidth(),
                            dogItemIndex = "${index + 1}",
                            dogName = dogName,
                            onItemClick = {
                                viewModel.sendIntent(
                                    DogListIntent.DogListItemClicked(
                                        dogName.dogBreedName,
                                        dogName.dogFullName,
                                    )
                                )
                            }
                        )
                    }
                }
            }

            is DogListViewState.Error -> {
                val errorMessage = currentState.message
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            is DogListViewState.Loading, DogListViewState.Idle -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.dogListClickState.collect { clickState ->
            if (clickState is DogListClickState.NavigateToDetailScreen) {
                val (breedName, dogName) = clickState
                callback.invoke(breedName, dogName)
            }
        }
    }

}

