package com.mvi.common.extensions

import java.util.Locale

fun String.capitalizeFirstLetter() : String {
    return if (isNotBlank()) substring(0, 1).uppercase(Locale.ROOT) + substring(1).lowercase(
        Locale.ROOT
    ) else this
}
