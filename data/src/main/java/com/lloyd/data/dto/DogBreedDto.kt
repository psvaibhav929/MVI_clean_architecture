package com.lloyd.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogBreedDto(
    val message: Map<String, List<String>>,
    val status: String
) : Parcelable