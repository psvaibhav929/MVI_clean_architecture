package com.lloyd.data.dto

import android.os.Parcelable
import com.lloyd.domain.model.DogDetails
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogDetailsDto(
    val message: String,
    val status: String
) : Parcelable
