package com.mvi.features_animal_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.mvi.common.extensions.capitalizeFirstLetter
import com.mvi.features_animal_details.viewmodel.DogDetailsViewModel
import com.mvi.features_animal_details.viewstate.DogDetailsViewState

const val TEST_TAG_DOG_DETAILS_SCREEN = "dog_details_screen"

@Composable
fun DogDetailsScreen(
    dogFullName: String?,
    viewModel: DogDetailsViewModel = hiltViewModel()
) {

    val state by viewModel.dogDetailsState.collectAsState(initial = DogDetailsViewState.Idle)

    val painter = rememberAsyncImagePainter(
        model = if (state is DogDetailsViewState.Success) {
            (state as DogDetailsViewState.Success).dogImageUrl
        } else {
            ""
        }
    )

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .testTag(TEST_TAG_DOG_DETAILS_SCREEN)
    ) {
        when (state) {
            is DogDetailsViewState.Success -> {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DogImage(
                            painter = painter, Modifier
                                .width(50.dp)
                                .height(50.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = dogFullName?.capitalizeFirstLetter() ?: "",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    DogImage(
                        painter = painter, Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }

            is DogDetailsViewState.Error -> {
                val errorState = state as DogDetailsViewState.Error
                Text(
                    text = errorState.message,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            is DogDetailsViewState.Loading, DogDetailsViewState.Idle -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

        }
    }
}