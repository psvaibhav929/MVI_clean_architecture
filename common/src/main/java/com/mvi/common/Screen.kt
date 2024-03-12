package com.mvi.common

import com.mvi.common.Constants.DOG_DETAILS_SCREEN
import com.mvi.common.Constants.DOG_LIST_SCREEN

sealed class Screen(val route: String) {
    object DogListScreen : Screen(DOG_LIST_SCREEN)
    object DogDetailScreen : Screen(DOG_DETAILS_SCREEN)
}