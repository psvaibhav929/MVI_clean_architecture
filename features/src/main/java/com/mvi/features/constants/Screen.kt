package com.mvi.features.constants

import com.mvi.features.constants.ScreenConstants.DOG_DETAILS_SCREEN
import com.mvi.features.constants.ScreenConstants.DOG_LIST_SCREEN

sealed class Screen(val route: String) {
    object DogListScreen : Screen(DOG_LIST_SCREEN)
    object DogDetailScreen : Screen(DOG_DETAILS_SCREEN)
}