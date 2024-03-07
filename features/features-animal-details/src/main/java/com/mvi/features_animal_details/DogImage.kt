package com.mvi.features_animal_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter

@Composable
internal fun DogImage(painter: AsyncImagePainter, modifier: Modifier) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painter,
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = modifier,
        )
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center).width(20.dp).height(20.dp))
        }
    }

}