package com.mvi.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mvi.common.constant.Constants
import com.mvi.features.constants.Screen
import com.mvi.features.theme.MainActivityTheme
import com.mvi.features_animal_details.DogDetailsScreen
import com.mvi.features_animal_list.DogListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            MainActivityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.DogListScreen.route
                    ) {
                        composable(
                            route = Screen.DogListScreen.route
                        ) {
                            DogListScreen(
                                { dogBreed, dogName ->
                                    navController.navigate(Screen.DogDetailScreen.route + "/$dogBreed/$dogName")
                                }
                            )
                        }
                        composable(
                            route = Screen.DogDetailScreen.route + "/{${Constants.PARAM_DOG_BREED_NAME}}" + "/{${Constants.PARAM_DOG_FULL_NAME}}",
                            arguments = listOf(
                                navArgument(Constants.PARAM_DOG_BREED_NAME) {
                                    type = NavType.StringType
                                },
                                navArgument(Constants.PARAM_DOG_FULL_NAME) {
                                    type = NavType.StringType
                                },
                            )
                        ) { navBackStackEntry ->
                            val dogFullName =
                                navBackStackEntry.arguments?.getString(Constants.PARAM_DOG_FULL_NAME)
                            DogDetailsScreen(dogFullName)
                        }
                    }
                }
            }
        }
    }
}
