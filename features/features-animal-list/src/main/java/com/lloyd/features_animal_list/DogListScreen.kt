package com.lloyd.features_animal_list

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.lloyd.common.Screen
import com.lloyd.features_animal_list.intent.DogListIntent
import com.lloyd.features_animal_list.viewmodel.DogListViewModel
import com.lloyd.features_animal_list.viewstate.DogListViewState


const val TEST_TAG_DOG_LIST_SCREEN = "dog_list_screen"

@Composable
fun DogListScreen(
    navController: NavController,
    viewModel: DogListViewModel = hiltViewModel()
) {

    val state = viewModel.dogListState.collectAsStateWithLifecycle().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TEST_TAG_DOG_LIST_SCREEN)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state is DogListViewState.Success) {
                itemsIndexed(state.data) { index, dogName ->
                    DogListItem(
                        modifier = Modifier.fillMaxWidth(),
                        dogItemIndex = "${index + 1}",
                        dogName = dogName,
                        onItemClick = {
                            navController.navigate(
                                Screen.DogDetailScreen.route + "/${dogName.dogBreedName}" + "/${dogName.dogFullName}"
                            )
                        }
                    )
                }
            }
        }
        if (state is DogListViewState.Error) {
            Text(
                text = state.message,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state is DogListViewState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

