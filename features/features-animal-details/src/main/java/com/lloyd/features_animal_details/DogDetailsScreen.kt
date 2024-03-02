package com.lloyd.features_animal_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.lloyd.features_animal_details.viewstate.DogDetailsViewState
import com.lloyd.features_animal_list.intent.DogDetailsIntent

const val TEST_TAG_DOG_DETAILS_SCREEN = "dog_details_screen"

@Composable
fun DogDetailsScreen(
    dogBreedName: String?,
    dogFullName: String?,
    viewModel: DogDetailsViewModel
) {
    LaunchedEffect(key1 = null, block = {
        dogBreedName?.let {
            viewModel.sendIntent(DogDetailsIntent.GetDogDetails(it))
        }
    })

    val state = viewModel.dogDetailsState.collectAsStateWithLifecycle().value
    if (state is DogDetailsViewState.Success) {

    }
    val painter = rememberAsyncImagePainter(
        model = if (state is DogDetailsViewState.Success) {
            state.dogImageUrl
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
        if (state is DogDetailsViewState.Success) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = dogFullName.toString(),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                ) {
                    Image(
                        painter = painter,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                    )
                    if (painter.state is AsyncImagePainter.State.Loading) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }
            }
        }

        if (state is DogDetailsViewState.Error) {
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
        if (state is DogDetailsViewState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}