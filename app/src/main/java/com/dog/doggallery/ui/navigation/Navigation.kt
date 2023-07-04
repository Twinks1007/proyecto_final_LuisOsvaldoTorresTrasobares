package com.dog.doggallery.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dog.doggallery.ui.screens.components.dog.DogDetails
import com.dog.doggallery.ui.screens.components.favorite.DogFavorite
import com.dog.doggallery.ui.screens.main.DogsApp
import com.dog.doggallery.viewModel.ViewModelDog

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Dog : Routes("dog")
    object Favorite : Routes("favorite")
}

@Composable
fun Navigation(viewModelDog: ViewModelDog) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route) {

        viewModelDog.getBreedsAll()
        viewModelDog.getRoomAllDogs()
        composable(Routes.Home.route) {
            DogsApp(
                navController= navController,
                viewModelDog = viewModelDog)
        }
        composable(Routes.Dog.route.plus("/{breed}")) {
            val breed = it.arguments?.getString("breed").orEmpty()
            DogDetails(
                navController= navController,
                breed = breed,
                viewModelDog = viewModelDog
            )
        }
        composable(Routes.Favorite.route) {
            DogFavorite(
                navController= navController,
                viewModelDog = viewModelDog
            )
        }
    }
}