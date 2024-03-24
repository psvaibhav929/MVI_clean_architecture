package com.mvi.features_animal_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mvi.common.extensions.capitalizeFirstLetter
import com.mvi.domain.model.DogName

@Composable
fun DogListItem(
    modifier: Modifier = Modifier,
    dogItemIndex: String,
    dogName: DogName,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(dogName.dogFullName) }
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = dogItemIndex,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = dogName.dogFullName.capitalizeFirstLetter()  ,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}