package com.dog.doggallery.ui.screens.main

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dog.doggallery.ui.theme.DogGalleryTheme
import com.dog.doggallery.viewModel.ViewModelDog

@Composable
fun DogsApp(navController: NavHostController, viewModelDog: ViewModelDog) {
    DogGalleryTheme() {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colorScheme.background) {
            MainApp(navController,viewModelDog)
        }
    }
}