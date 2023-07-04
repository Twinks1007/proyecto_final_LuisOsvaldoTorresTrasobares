package com.dog.doggallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.dog.doggallery.database.DogRoomDatabase
import com.dog.doggallery.repository.DogRepository
import com.dog.doggallery.repository.DogRoomRepository
import com.dog.doggallery.ui.navigation.Navigation
import com.dog.doggallery.viewModel.DogViewModelFactory
import com.dog.doggallery.viewModel.ViewModelDog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {

    val applicationScope = CoroutineScope(Dispatchers.IO)

    val database by lazy { DogRoomDatabase.getDatabase(
        context = this,
        scope = applicationScope)
    }

    val repository by lazy { DogRoomRepository(database.dogDao()) }

    private val viewModelDog by viewModels<ViewModelDog>{
        DogViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation(viewModelDog)
        }
    }
}

