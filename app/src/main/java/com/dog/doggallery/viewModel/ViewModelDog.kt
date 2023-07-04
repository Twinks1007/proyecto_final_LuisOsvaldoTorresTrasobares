package com.dog.doggallery.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dog.doggallery.model.Dog
import com.dog.doggallery.repository.DogRepository
import com.dog.doggallery.repository.DogRoomRepository
import com.dog.doggallery.utils.convertListToAllDogs
import com.dog.doggallery.utils.dogEntityToDog
import com.dog.doggallery.utils.dogToDogEntity
import com.dog.doggallery.utils.dogsEntityToDogs
import com.dog.doggallery.utils.stringImageOrStringsImages
import com.dog.doggallery.utils.stringsImagesTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ViewModelDog(var roomRepository: DogRoomRepository) : ViewModel() {

    private val repository = DogRepository()


    private val _dogs = MutableLiveData<List<String>>()
    val dogs: LiveData<List<String>?> = _dogs


    private val _dogsHome = MutableLiveData<ArrayList<Dog>>()
    val dogsHome: LiveData<ArrayList<Dog>?> = _dogsHome

    private val _dogsRoom = MutableLiveData<ArrayList<Dog>>()
    val dogsRoom: LiveData<ArrayList<Dog>?> = _dogsRoom

    private val _dog = MutableLiveData<Dog>()
    val dog: LiveData<Dog> = _dog


    private val _dogsDetail = MutableLiveData<List<String>>()
    val dogsDetail: LiveData<List<String>> = _dogsDetail


    fun getBreedsAll() {
        viewModelScope.launch {
            try {
                val dogs = repository.getBreedsAll()
                _dogs.value = convertListToAllDogs(dogs.message)
                getDogHome()
            }catch (e: Exception) {
                Log.e("getBreedsAll", e.message.toString());
            }
        }
    }

    fun getDogBreedImages(breeds:String){

        viewModelScope.launch {
            try {
                if (stringImageOrStringsImages(breeds)){
                    val images = repository.getdogBreedImagesService(breeds).message
                    _dogsDetail.value = images
                }else{
                    val transformation = stringsImagesTransformation(breeds)
                    val images = repository.getdogBreedsImagesService(transformation.breeds,transformation.hound).message
                    _dogsDetail.value = images
                }

            }catch (e: Exception) {
                Log.e("getDogBreedImages", e.message.toString());
            }

        }
    }

    private fun getDogHome() {
        val dogsHome = arrayListOf<Dog>()
        viewModelScope.launch {
            try {
                dogs.value?.shuffled()?.take(5)?.forEach {
                    if (stringImageOrStringsImages(it)){
                        val dog = repository.getdogBreedImageService(it)
                        dogsHome.add(Dog(name = it, image = dog.message))
                    }else{
                        val transformation = stringsImagesTransformation(it)
                        val dog = repository.getdogBreedsImageService(breeds = transformation.breeds, hound = transformation.hound)
                        dogsHome.add(Dog(name = it, image = dog.message))
                    }

                }
                _dogsHome.value = dogsHome
            }catch (e: Exception) {
                Log.e("getDogHome", e.message.toString());
            }
        }
    }

     fun getRoomAllDogs(){
         viewModelScope.launch(Dispatchers.Main) {
             val data = roomRepository.allDogs.first()
             _dogsRoom.value = dogsEntityToDogs(data)
         }

    }

    fun insertRoomDog(dog: Dog){
        viewModelScope.launch(Dispatchers.IO){
           roomRepository.insert(dogToDogEntity(dog))
        }
        getRoomAllDogs()
    }

    fun deleteRoomDog(dog: Dog){
        viewModelScope.launch(Dispatchers.IO){
            roomRepository.delete(dog.name)
        }
        getRoomAllDogs()
    }
}

class DogViewModelFactory(private val repository: DogRoomRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelDog::class.java)) {
            return ViewModelDog(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}